import React, { useEffect, useState } from 'react';
import { Routes, Route, NavLink, useNavigate } from "react-router-dom";
import Slider from "react-slick";
// import $ from "jquery";
import './App.css';
import Login from './components/Login';
import Redirect from './components/Redirect';
import Redirect2 from './components/Redirect2';
import ModalStory from './components/ModalStory';
import Menu from './components/Menu';
import NameOfPlanet from './components/NameOfPlanet';

// function DayCount() {

//   const [Dday, setDday] = useState('아직 편지 받을 준비가 되지 않았어요.');

//   function setDDay() {
//     // Set the date we're counting down to
//     let today = new Date();
//     let thatDay = new Date();
//     thatDay.setDate(today.getDate() + 10);
//     let DDay = new Date(thatDay).getTime();
//     // Update the count down every 1 second
//     let count = setInterval(function () {
//       // Get today's date and time
//       let now = new Date().getTime();
//       // Find the distance between now and the count down date
//       let distance = DDay - now;
//       // Time calculations for days, hours, minutes and seconds
//       let days = Math.floor(distance / (1000 * 60 * 60 * 24));
//       let hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
//       let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
//       let seconds = Math.floor((distance % (1000 * 60)) / 1000);
//       // Output the result in an element with id="Dday"
//       setDday(days + '일 ' + hours + '시간 ' + minutes + '분 ' + seconds + '초');
//       // If the count down is over, write some text
//       if (distance < 0) {
//         clearInterval(count);
//         setDday("편지를 열어보세요.");
//       }
//     }, 1000);
//   };

//   return (
//     <React.Fragment>
//       <button onClick={setDDay}>Start D-day</button>
//       <h5>{Dday}</h5>
//     </React.Fragment>
//   );
// };

function Main() {

  const [renderPage, setRenderPage] = useState(0);
  const [checkStory, setCheckStory] = useState(true);
  const [userData, setUserData] = useState({ "nickname": 'null', "openDate": null });
  const [letterData, setLetterData] = useState([]);

  // Delete Cookie
  function delCookie() {
    const expires = new Date();
    expires.setDate(expires.getDate() - 1);
    const expiresDate = expires.toUTCString();
    let setCookie = '';
    setCookie += 'planetter=190301;';
    setCookie += 'Expires=' + expiresDate;
    document.cookie = setCookie;
  };

  function reloading() {
    fetch('letterData.json')
      .then((result) => {
        return result.json();
      })
      .then((json) => {
        setLetterData(json);
      });
  };

  function goTof() {
    let newDate = userData;
    newDate['openDate'] = 1661837285640;
    setUserData(newDate);
    setRenderPage(renderPage + 1);
  };

  useEffect(() => {
    const cookieData = document.cookie.split(';');
    for (let i = 0; i < cookieData.length; i++) {
      if (cookieData[i].indexOf('planetter') > -1) {
      } else {
        setCheckStory(false);
      } break;
    };
  }, []);

  return (
    <div className='main_background'>
      <ModalStory props={{ checkStory, setCheckStory }}></ModalStory>
      <Menu props={userData}></Menu>
      <NameOfPlanet props={{ userData, setUserData, letterData, setLetterData }}></NameOfPlanet>
      <h1 className='loging'><NavLink end to="/login"></NavLink></h1>
      <button className='cookie' onClick={delCookie}></button>
      <button className='letter' onClick={reloading}></button>
      <button className='clock' onClick={goTof}></button>
    </div>
  );
};

function App() {

  const navigater = useNavigate();
  const [userID, setUserID] = useState(1);

  useEffect(() => {
    // Check user device.
    if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
    } else {
      alert("모바일 환경에 최적화 되어 있습니다.");
    };
    if (userID === null) {
      navigater('/login');
    } else {
    };
  }, [navigater, userID])

  return (
    <React.Fragment>
      <Routes>
        <Route path="/" element={<Main />}></Route>
        <Route path="/*" element={<Main />}></Route>
        <Route path="/login" element={<Login props={userID} />}></Route>
        <Route path="/redirect" element={<Redirect props={setUserID} />}></Route>
        <Route path="/redirect2" element={<Redirect2 props={setUserID} />}></Route>
      </Routes>
    </React.Fragment>
  );
};

export default App;