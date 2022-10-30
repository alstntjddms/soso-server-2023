import React, { useCallback, useEffect, useState } from 'react';
import Slider from "react-slick";
import './NameOfPlanet.css';

function NameOfPlanet(props) {

    const [checkOpenDate, setCheckOpenDate] = useState(true);
    const [renderPage, setRenderPage] = useState(0);
    const [yesNameUrl, setYesNameUrl] = useState(false);

    useEffect(() => {
        if (props.props.userData.openDate === null) {
            setCheckOpenDate(true);
        } else if (props.props.userData.openDate !== null) {
            setCheckOpenDate(false);
        };
    }, [props.props.userData.openDate]);


    function ShareButton(props) {

        const [checkMainButton, setCheckMainButton] = useState(false);
        const [checkShare, setCheckShare] = useState(false);

        useEffect(() => {
            if (props.props.props.props.userData.openDate !== null) {
                setCheckMainButton(true);
            };
        }, [props.props.props.props.userData.openDate]);

        const basicURL = 'https://angelo-s-library-2.netlify.app/';
        const image_share = 'https://cdn-icons-png.flaticon.com/512/1111/1111905.png';
        const title = 'Planetter - 기다려지는 소식';

        // function of to share throught the kakaotalk message
        function kakaoShare() {
            const KakaoJS = process.env.REACT_APP_JAVASCRIPT_KEY
            if (!window.Kakao.isInitialized()) {
                window.Kakao.init(KakaoJS);
            };
            window.Kakao.Share.sendDefault({
                objectType: 'feed',
                content: {
                    title: 'Planetter',
                    description: 'Planetter-기다려지는 소식',
                    imageUrl: image_share,
                    link: {
                        mobileWebUrl: basicURL,
                        webUrl: basicURL,
                    },
                },
                buttons: [
                    {
                        title: 'Planetter-편지 보내기',
                        link: {
                            mobileWebUrl: basicURL,
                            webUrl: basicURL,
                        },
                    },
                    {
                        title: 'Planetter-놀러가기',
                        link: {
                            mobileWebUrl: basicURL,
                            webUrl: basicURL,
                        },
                    },
                ],
                installTalk: true,
            });
        };

        // function of to share throught the twitter
        function shareTwitter() {
            let url = encodeURIComponent(basicURL);
            window.open(`https://twitter.com/intent/tweet?url=${url}&text=${title}`, '소소한 프로젝트', 'width=400, height=400');
        };

        // function of to share throught the facebook
        function shareFacebook() {
            window.open('https://www.facebook.com/sharer/sharer.php?u='
                + encodeURIComponent(basicURL)
                + '&t=' + encodeURIComponent(title),
                'facebooksharedialog',
                'menubar=no, toolbar=no, resizable=yes, scrollbars=yes, height=400, width=400');
        };

        function urlCopy() {
            var Dummy_Tag = document.createElement("input");
            var Current_URL = window.location.href;
            document.body.appendChild(Dummy_Tag);
            Dummy_Tag.value = Current_URL;
            Dummy_Tag.select();
            document.execCommand("copy");
            document.body.removeChild(Dummy_Tag);
            alert("링크가 복사되었습니다.\nThe link has been copied.");
        };

        return (
            <React.Fragment>
                <div className={checkMainButton ? 'shareButton_outContainer_active' : 'shareButton_outContainer'} onClick={() => { setCheckShare(!checkShare) }}>
                    <img className='shareButton_share' alt='share' src='https://cdn-icons-png.flaticon.com/512/929/929539.png'></img>
                </div>
                <div className='shareButton_innerContainer'>
                    <img className={checkShare ? 'shareButton_share_btn_url_active' : 'shareButton_share_btn'} alt='url' src='https://cdn-icons-png.flaticon.com/512/4050/4050374.png' onClick={urlCopy}></img>
                    <img className={checkShare ? 'shareButton_share_btn_kakao_active' : 'shareButton_share_btn'} alt='kakao' src='https://cdn-icons-png.flaticon.com/512/3669/3669990.png' onClick={kakaoShare}></img>
                    <img className={checkShare ? 'shareButton_share_btn_facebook_active' : 'shareButton_share_btn'} alt='facebook' src='https://cdn-icons-png.flaticon.com/512/2168/2168281.png' onClick={shareFacebook}></img>
                    <img className={checkShare ? 'shareButton_share_btn_twitter_active' : 'shareButton_share_btn'} alt='twitter' src='https://cdn-icons-png.flaticon.com/512/2168/2168336.png' onClick={shareTwitter}></img>
                </div>
            </React.Fragment>
        );
    };

    function YesNameUrl() {

        function urlCopy() {
            var Dummy_Tag = document.createElement("input");
            var Current_URL = window.location.href;
            document.body.appendChild(Dummy_Tag);
            Dummy_Tag.value = Current_URL;
            Dummy_Tag.select();
            document.execCommand("copy");
            document.body.removeChild(Dummy_Tag);
            alert("링크가 복사되었습니다.\nThe link has been copied.");
            setYesNameUrl(!yesNameUrl);
        };

        return (
            <div className={yesNameUrl ? "yesNameUrl" : "yesNameUrl_fade"}>
                <div className='yesNameUrl_outContainer'>
                    <img alt='close' className='yesNameUrl_img' src='https://cdn-icons-png.flaticon.com/512/463/463612.png' onClick={() => { setYesNameUrl(!yesNameUrl) }}></img>
                    <p className='yesNameUrl_title'>행성 개설 완료!</p>
                    <p className='yesNameUrl_p'>신호를 공유해 편지를 받아보세요!</p>
                    <div className='yesNameUrl_button' onClick={urlCopy}>링크 복사</div>
                </div>
            </div>
        );
    };

    function NoName(props) {

        const [viewSend, setViewSend] = useState(true);
        const [changeUserNickname, setChangeUserNickname] = useState(String(props.props.props.props.userData.nickname));
        const [inputUserNameLength, setInputUserNameLength] = useState(Number(props.props.props.props.userData.nickname.length));
        const [startMonth, setStartMonth] = useState(null);
        const [startHours, setStartHours] = useState(null);
        const [endMonth, setEndMonth] = useState(null);
        const [endHours, setEndHours] = useState(null);
        const [startDate, setStartDate] = useState(null);

        function settingStartDate() {
            const now = new Date();
            now.setDate(now.getDate() + 10);
            setStartDate(now.getTime());
        };

        function settingStartMonth() {
            setInterval(function () {
                let now = new Date();
                let months = now.getMonth() + 1;
                let days = now.getDate();
                setStartMonth(months + '-' + days);
            }, 1000);
        };

        function settingStartHours() {
            setInterval(function () {
                let now = new Date();
                let hours = now.getHours();
                let minutes = now.getMinutes();
                let seconds = now.getSeconds();
                setStartHours(hours + ':' + minutes + ':' + seconds);
            }, 1000);
        };

        function settingEndtMonth() {
            setInterval(function () {
                let now = new Date();
                now.setDate(now.getDate() + 10)
                let months = now.getMonth() + 1;
                let days = now.getDate();
                setEndMonth(months + '-' + days);
            }, 1000);
        };

        function settingEndHours() {
            setInterval(function () {
                let now = new Date();
                let hours = now.getHours();
                let minutes = now.getMinutes();
                let seconds = now.getSeconds();
                setEndHours(hours + ':' + minutes + ':' + seconds);
            }, 1000);
        };

        function showSettingPage() {
            setViewSend(!viewSend);
            settingStartDate();
            settingStartMonth();
            settingStartHours();
            settingEndtMonth();
            settingEndHours();
        };

        function nonshowSettingPage() {
            const originalUserNickname = String(props.props.props.props.userData.nickname);
            const originalUserNicknameLength = Number(props.props.props.props.userData.nickname.length);
            setChangeUserNickname(originalUserNickname);
            setInputUserNameLength(originalUserNicknameLength);
            setViewSend(!viewSend);
        };

        function settingUserData() {
            if (window.confirm(`${changeUserNickname}(이)란 이름으로 행성을 개설할까요?`)) {
                let newUserData = props.props.props.props.userData;
                newUserData['nickname'] = changeUserNickname;
                newUserData['openDate'] = startDate;
                props.props.props.props.setUserData(newUserData);
                setViewSend(!viewSend);
                setYesNameUrl(!yesNameUrl);
                setRenderPage(renderPage + 1);
            };
        };

        return (
            <div className='noname_outContainer'>
                <h3>아직 신호를 보내지 않았습니다.</h3>
                <div className='noname_sendSignal' onClick={showSettingPage}>신호 보내기</div>
                <div className={viewSend ? "noname_sendSignal_outContainer_fade" : "noname_sendSignal_outContainer"}>
                    <div className='noname_sendSignal_innerTitle'>
                        <img alt='back_icon' className='noname_sendSignal_innerTitle_backIcon' src='https://cdn-icons-png.flaticon.com/512/8287/8287941.png' onClick={nonshowSettingPage}></img>
                        <p className='noname_sendSignal_innerTitle_p'>안녕, 잘 지내?
                            <br></br>
                            나는…
                        </p>
                    </div>
                    <div className='noname_sendSignal_innerName'>
                        <p className='noname_sendSignal_innerName_p'>행성의 이름</p>
                        <input id='input_userName' maxLength={10} onChange={(e) => {
                            setInputUserNameLength(e.target.value.length)
                            setChangeUserNickname(e.target.value)
                        }} value={changeUserNickname}></input><span className='inputUserNameLength'>{inputUserNameLength}/10</span>
                    </div>
                    <div className='noname_sendSignal_line'></div>
                    <div className='noname_sendSignal_innerTime'>
                        <p className='noname_sendSignal_innerTime_p'>시작 - 마감</p>
                        <h6 className='noname_sendSignal_innerTime_h6'>Planetter는 10일의 시간을 제공합니다.</h6>
                    </div>
                    <div className='noname_sendSignal_innerTime_view'>
                        <div className='noname_sendSignal_innerTime_view_start'>
                            <p className='noname_sendSignal_innerTime_view_p'>시작</p>
                            <p className='noname_sendSignal_innerTime_view_month'>{startMonth}</p>
                            <p className='noname_sendSignal_innerTime_view_hours'>{startHours}</p>
                        </div>
                        <div className='noname_sendSignal_innerTime_view_pause'>~</div>
                        <div className='noname_sendSignal_innerTime_view_end'>
                            <p className='noname_sendSignal_innerTime_view_p'>마감</p>
                            <p className='noname_sendSignal_innerTime_view_month'>{endMonth}</p>
                            <p className='noname_sendSignal_innerTime_view_hours'>{endHours}</p>
                        </div>
                    </div>
                    <div className='noname_sendSignal_line'></div>
                    <div className='noname_sendSignal_innerNotice'>
                        <div className='noname_sendSignal_innerNotice_innerbox'>
                            <p className='noname_sendSignal_innerNotice_p'>알림 설정</p>
                            <h6 className='noname_sendSignal_innerNotice_h6'>카카오톡을 통해 D-Day 알림을 받습니다.</h6>
                        </div>
                        <div>
                            <img alt='button_notice' className='noname_sendSignal_innerNotice_button' src='https://cdn-icons-png.flaticon.com/512/5720/5720465.png' onClick={() => { alert('아직 서비스 준비 중입니다.') }}></img>
                        </div>
                    </div>
                    <div className='noname_sendSignal_startDiv'>
                        <div className='noname_sendSignal_startDiv_button' onClick={settingUserData}>시작하기</div>
                    </div>
                </div>
            </div>
        );
    };

    function YesName(props) {

        const [Dday, setDday] = useState(Number(props.props.props.props.userData.openDate));

        const setDDay = useCallback(() => {
            let count = setInterval(function () {
                // Get today's date and time
                let now = new Date().getTime();
                // Find the distance between now and the count down date
                let distance = Dday - now;
                // Time calculations for days, hours, minutes and seconds
                let days = Math.floor(distance / (1000 * 60 * 60 * 24));
                let hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                let seconds = Math.floor((distance % (1000 * 60)) / 1000);
                // Output the result in an element with id="Dday"
                setDday(days + '일 ' + hours + '시간 ' + minutes + '분 ' + seconds + '초');
                // If the count down is over, write some text
                if (distance < 0) {
                    clearInterval(count);
                    setDday("편지를 열어보세요.");
                };
            }, 0);
        }, [Dday]);

        useEffect(() => {
            setDDay();
        }, []);

        return (
            <div className='yesname_outContainer'>
                <h3>P-{props.props.props.props.userData.nickname}</h3>
                <h4>남은 시간</h4>
                <h4>{Dday}</h4>
            </div>
        );
    };

    function Letter(props) {

        return (
            <div>
                {/* <div onClick={() => { props.props.setLetterModal(!props.props.letterModal) }}>
                    <div className={"modallettermodal" + props.props.props.props.props.props.letterData[props.props.letterId - 1].fontColor}>
                        <h1 className="modal_titlelettermodal">{"From: " + props.props.props.props.props.props.letterData[props.props.letterId - 1].author}</h1>
                        <span className={'abcdefg' + props.props.props.props.props.props.letterData[props.props.letterId - 1].fontFamily}>
                            <article className={"modal_desclettermodal" + props.props.props.props.props.props.letterData[props.props.letterId - 1].fontColor}>
                                {"Letter Description: " + props.props.props.props.props.props.letterData[props.props.letterId - 1].desc}
                                <br></br>
                                {"Letter Font Color: " + props.props.props.props.props.props.letterData[props.props.letterId - 1].fontColor}
                                <br></br>
                                {"Letter Font Family: " + props.props.props.props.props.props.letterData[props.props.letterId - 1].fontFamily}
                            </article>
                        </span>
                        <h3 className="modal_authorlettermodal"></h3>
                        <div className="modal_closelettermodal" onClick={() => { props.props.setLetterModal(!props.props.letterModal) }}></div>
                    </div>
                </div> */}
            </div>
        );
    };

    function LetterBox(props) {

        const [renderPage, setRenderPage] = useState(0);
        const [letterModal, setLetterModal] = useState(false);
        const [letterId, setLetterId] = useState(null);
        const [list, setList] = useState([<span key={1} style={{ color: "white" }}>Loading...</span>]);
        const [list2, setList2] = useState([<span key={2} style={{ color: "white" }}>Loading...</span>]);
        const [list3, setList3] = useState([<span key={3} style={{ color: "white" }}>Loading...</span>]);
        const [list4, setList4] = useState([<span key={4} style={{ color: "white" }}>Loading...</span>]);

        function openLetter(letterData, i) {
            let now = new Date().getTime();
            let distance = props.props.props.props.userData.openDate - now;
            if (distance < 0) {
                alert('편지를 읽다')
                if (i === 6 || i === 14 || i === 22 || i === 30) {
                    let props = letterData;
                    let propsObj = {
                        "num": letterData[i].num, "desc": letterData[i].desc, "fontColor": letterData[i].fontColor, "fontFamily": letterData[i].fontFamily, "letterIcon": "open1", "openOrNot": "true", "author": letterData[i].author
                    };
                    props[i] = propsObj;
                    setLetterModal(!letterModal);
                    setLetterId(Number(letterData[i].num));
                    setRenderPage(renderPage + 1);
                } else if (i === 7 || i === 15 || i === 23 || i === 31) {
                    let props = letterData;
                    let propsObj = {
                        "num": letterData[i].num, "desc": letterData[i].desc, "fontColor": letterData[i].fontColor, "fontFamily": letterData[i].fontFamily, "letterIcon": "open2", "openOrNot": "true", "author": letterData[i].author
                    };
                    props[i] = propsObj;
                    setLetterModal(!letterModal);
                    setLetterId(Number(letterData[i].num));
                    setRenderPage(renderPage + 1);
                } else if (-1 < i < 6 || 7 < i < 14 || 15 < i < 22 || 23 < i < 30) {
                    let props = letterData;
                    let propsObj = {
                        "num": letterData[i].num, "desc": letterData[i].desc, "fontColor": letterData[i].fontColor, "fontFamily": letterData[i].fontFamily, "letterIcon": "open", "openOrNot": "true", "author": letterData[i].author
                    };
                    props[i] = propsObj;
                    setLetterModal(!letterModal);
                    setLetterId(Number(letterData[i].num));
                    setRenderPage(renderPage + 1);
                };
            } else {
                alert('아직 못 읽어요.');
            };
        };

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

        useEffect(() => {
            const list = [];
            const list2 = [];
            const list3 = [];
            const list4 = [];

            if (props.props.props.props.letterData.length === 0) {
                setList(<span style={{ color: "black" }}>Nothing...</span>)
                setList2(<span style={{ color: "black" }}>Nothing...</span>)
                setList3(<span style={{ color: "black" }}>Nothing...</span>)
                setList4(<span style={{ color: "black" }}>Nothing...</span>)
            } else if (props.props.props.props.letterData.length <= 8) {
                for (let i = 0; i < props.props.props.props.letterData.length; i++) {
                    let li = props.props.props.props.letterData[i]
                    list.push(React.Children.toArray(<button key={li.num} data-id={li.num} className={"letter" + li.letterIcon} onClick={() => { openLetter(props.props.props.props.letterData, i) }}></button>))
                    setList(list)
                }
            } else if (props.props.props.props.letterData.length <= 16) {
                for (let i = 0; i < 8; i++) {
                    let li = props.props.props.props.letterData[i]
                    list.push(React.Children.toArray(<button key={li.num} data-id={li.num} className={"letter" + li.letterIcon} onClick={() => { openLetter(props.props.props.props.letterData, i) }}></button>))
                    setList(list)
                }

                for (let i = 8; i < props.props.props.props.letterData.length; i++) {
                    let li = props.props.props.props.letterData[i]
                    list2.push(React.Children.toArray(<button key={li.num} data-id={li.num} className={"letter" + li.letterIcon} onClick={() => { openLetter(props.props.props.props.letterData, i) }}></button>))
                    setList2(list2)
                }
            } else if (props.props.props.props.letterData.length <= 24) {
                for (let i = 0; i < 8; i++) {
                    let li = props.props.props.props.letterData[i]
                    list.push(React.Children.toArray(<button key={li.num} data-id={li.num} className={"letter" + li.letterIcon} onClick={() => { openLetter(props.props.props.props.letterData, i) }}></button>))
                    setList(list)
                }

                for (let i = 8; i < 16; i++) {
                    let li = props.props.props.props.letterData[i]
                    list2.push(React.Children.toArray(<button key={li.num} data-id={li.num} className={"letter" + li.letterIcon} onClick={() => { openLetter(props.props.props.props.letterData, i) }}></button>))
                    setList2(list2)
                }
                for (let i = 16; i < props.props.props.props.letterData.length; i++) {
                    let li = props.props.props.props.letterData[i]
                    list3.push(React.Children.toArray(<button key={li.num} data-id={li.num} className={"letter" + li.letterIcon} onClick={() => { openLetter(props.props.props.props.letterData, i) }}></button>))
                    setList3(list3)
                }

            } else if (props.props.props.props.letterData.length <= 32) {
                for (let i = 0; i < 8; i++) {
                    let li = props.props.props.props.letterData[i]
                    list.push(React.Children.toArray(<button key={li.num} data-id={li.num} className={"letter" + li.letterIcon} onClick={() => { openLetter(props.props.props.props.letterData, i) }}></button>))
                    setList(list)
                }

                for (let i = 8; i < 16; i++) {
                    let li = props.props.props.props.letterData[i]
                    list2.push(React.Children.toArray(<button key={li.num} data-id={li.num} className={"letter" + li.letterIcon} onClick={() => { openLetter(props.props.props.props.letterData, i) }}></button>))
                    setList2(list2)

                }
                for (let i = 16; i < 24; i++) {
                    let li = props.props.props.props.letterData[i]
                    list3.push(React.Children.toArray(<button key={li.num} data-id={li.num} className={"letter" + li.letterIcon} onClick={() => { openLetter(props.props.props.props.letterData, i) }}></button>))
                    setList3(list3)

                }
                for (let i = 24; i < props.props.props.props.letterData.length; i++) {
                    let li = props.props.props.props.letterData[i]
                    list4.push(React.Children.toArray(<button key={li.num} data-id={li.num} className={"letter" + li.letterIcon} onClick={() => { openLetter(props.props.props.props.letterData, i) }}></button>))
                    setList4(list4)

                }
            } else {
                console.log("I'm full")
            }
        }, [props.props.props.props.letterData, renderPage])

        return (
            <React.Fragment>
                {letterModal && <Letter props={{ props, letterModal, setLetterModal, letterId }}></Letter>}
                <div className='letterBox_outContainer'>
                    <Slider {...settings}>
                        <div className='letterBox_innerContainer'>
                            <div className='letterBox_innerContainer_div'>{list}
                            </div>
                        </div>
                        <div className='letterBox_innerContainer'>
                            <div className='letterBox_innerContainer_div'>{list2}
                            </div>
                        </div>
                        <div className='letterBox_innerContainer'>
                            <div className='letterBox_innerContainer_div'>{list3}
                            </div>
                        </div>
                        <div className='letterBox_innerContainer'>
                            <div className='letterBox_innerContainer_div'>{list4}
                            </div>
                        </div>
                    </Slider>
                </div>
            </React.Fragment>
        );
    };

    return (
        <React.Fragment>
            <ShareButton props={{ props }}></ShareButton>
            {checkOpenDate ? <NoName props={{ props }}></NoName> : <YesName props={{ props }}></YesName>}
            {checkOpenDate ? '' : <LetterBox props={{ props }}></LetterBox>}
            <YesNameUrl></YesNameUrl>
        </React.Fragment>
    );
};

export default NameOfPlanet;