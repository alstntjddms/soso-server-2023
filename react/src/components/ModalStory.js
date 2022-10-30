import React, { useState } from 'react';
import Slider from "react-slick";
import './ModalStory.css';

function ModalStory(props) {

  const [isChecked, setIsChecked] = useState(false);

  let settings = {
    draggable: false,
    swipe: false,
    arrows: true,
    dots: true,
    infinite: false,
    speed: 1250,
    slidesToShow: 1,
    slidesToScroll: 1
  };

  function toggleCheck() {
    props.props.setCheckStory(!props.props.checkStory);
  };

  // Setting Cookie
  function setCookie() {
    setIsChecked(!isChecked);
    if (isChecked === false) {
      const expires = new Date();
      expires.setDate(expires.getDate() + 10);
      const expiresDate = expires.toUTCString();
      let setCookie = '';
      setCookie += 'planetter=190301;';
      setCookie += 'Expires=' + expiresDate;
      document.cookie = setCookie;
    } else {
      delCookie();
    };
  };

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

  return (
    <div className={props.props.checkStory ? "modalStory_fade" : "modalStory_nonFade"}>
      <div className="modalStory_outContainer">
        <div className="modalStory_innerContainer">
          <div className="modalStory_close" onClick={toggleCheck}></div>
          <Slider {...settings}>
            <div className='modalStory_outDiv'>
              <div>
                <div className='modalStory_img1'></div>
                <p className="modalStory_p">"외로워…"</p>
                <p className="modalStory_p">작은 별이 달에게 푸념했습니다.</p>
              </div>
            </div>
            <div className='modalStory_outDiv'>
              <div>
                <div className='modalStory_img2'></div>
                <p className="modalStory_p">"옛날에는 지구인이 종종 찾아오곤 했단다."</p>
                <p className="modalStory_p">달은 작은 별에게 지구의 이야기를 들려줬습니다.</p>
              </div>
            </div>
            <div className='modalStory_outDiv'>
              <div>
                <div className='modalStory_img3'></div>
                <p className="modalStory_p">달의 이야기를 들은 작은 별은 지구인들이 궁금해졌습니다.</p>
                <p className="modalStory_p">그래서 작은 별은 지구인들에게 신호를 보내기로 결심했어요.</p>
              </div>
            </div>
            <div className='modalStory_outDiv'>
              <div>
                <div className='modalStory_img4'></div>
                <p className="modalStory_p">"띠띠…띠 나에게 편지를 보내줘. 내 이름은 ― 지지직! "</p>
                <div className='modalStory_startButton' onClick={toggleCheck}>시작하기</div>
              </div>
            </div>
          </Slider>
          <div className='modalStory_cookie'><input className='modalStory_cookie_input' type='checkbox' checked={isChecked} onChange={setCookie}></input> 다시 보지 않기(10일 동안)</div>
        </div>
      </div>
    </div>
  );
};

export default ModalStory;