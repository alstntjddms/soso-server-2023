import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import './Login.css';

function Login(props) {

    const navigater = useNavigate();
    const [isConditions, setIsConditions] = useState(false);
    const [isIndividual, setIsIndividual] = useState(false);

    useEffect(() => {
        if (props.props !== null) {
            navigater('/main')
        };
    }, [navigater, props.props]);

    function loginWithKakao(key, url) {
        window.location.href = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${key}&redirect_uri=${url}`;
    };

    function toggleConditions() {
        setIsConditions(!isConditions);
    };

    function toggleIndividual() {
        setIsIndividual(!isIndividual);
    };

    function Conditions(props) {

        function toggleConditions() {
            props.props.setIsConditions(!props.props.isConditions);
        };

        return (
            <div className={props.props.isConditions ? "login_conditions" : "login_conditions_fade"}>
                <div className='login_conditions_outContainer'>
                    <img alt='close' className='login_conditions_img' src='https://cdn-icons-png.flaticon.com/512/463/463612.png' onClick={toggleConditions}></img>
                    <p className='login_conditions_title'>이용약관</p>
                    <article className='login_conditions_article'>
                        <p>
                            1. 동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 무궁화 삼천리 화려 강산 대한 사람 대한으로 길이 보전하세
                        </p>
                        <p>
                            2. 남산 위에 저 소나무 철갑을 두른 듯 바람 서리 불변함은 우리 기상일세 무궁화 삼천리 화려 강산 대한 사람 대한으로 길이 보전하세
                        </p>
                        <p>
                            3. 가을 하늘 공활한데 높고 구름 없이 밝은 달은 우리 가슴 일편단심일세 무궁화 삼천리 화려 강산 대한 사람 대한으로 길이 보전하세
                        </p>
                        <p>
                            4. 이 기상과 이 맘으로 충성을 다하여 괴로우나 즐거우나 나라 사랑하세 무궁화 삼천리 화려 강산 대한 사람 대한으로 길이 보전하세
                        </p>
                        <p>
                            1. 동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 무궁화 삼천리 화려 강산 대한 사람 대한으로 길이 보전하세
                        </p>
                    </article>
                </div>
            </div>
        );
    };

    function Individual(props) {

        function toggleIndividual() {
            props.props.setIsIndividual(!props.props.isIndividual);
        };

        return (
            <div className={props.props.isIndividual ? "login_conditions" : "login_conditions_fade"}>
                <div className='login_conditions_outContainer'>
                    <img alt='close' className='login_conditions_img' src='https://cdn-icons-png.flaticon.com/512/463/463612.png' onClick={toggleIndividual}></img>
                    <p className='login_conditions_title'>개인정보처리방침</p>
                    <article className='login_conditions_article'>
                        <p>
                            1. 동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 무궁화 삼천리 화려 강산 대한 사람 대한으로 길이 보전하세
                        </p>
                        <p>
                            2. 남산 위에 저 소나무 철갑을 두른 듯 바람 서리 불변함은 우리 기상일세 무궁화 삼천리 화려 강산 대한 사람 대한으로 길이 보전하세
                        </p>
                        <p>
                            3. 가을 하늘 공활한데 높고 구름 없이 밝은 달은 우리 가슴 일편단심일세 무궁화 삼천리 화려 강산 대한 사람 대한으로 길이 보전하세
                        </p>
                        <p>
                            4. 이 기상과 이 맘으로 충성을 다하여 괴로우나 즐거우나 나라 사랑하세 무궁화 삼천리 화려 강산 대한 사람 대한으로 길이 보전하세
                        </p>
                        <p>
                            1. 동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 무궁화 삼천리 화려 강산 대한 사람 대한으로 길이 보전하세
                        </p>
                    </article>
                </div>
            </div>
        );
    };

    return (
        <React.Fragment>
            <Conditions props={{ isConditions, setIsConditions }}></Conditions>
            <Individual props={{ isIndividual, setIsIndividual }}></Individual>
            <div className='login_wrap'>
                <div className='login_outContainer'>
                    <div className='login_innerContainer'>
                        <img alt='login_img' className='login_img' src='https://cdn-icons-png.flaticon.com/512/6598/6598519.png'></img>
                        <h4 className='login_title'>로그인 해서 편지를 받아보세요</h4>
                        <img alt='login_kakao' className='login_kakao' src='http://papaspick.com/web/upload/2019_web/icon/kakao_login.jpg' onClick={() => { loginWithKakao(process.env.REACT_APP_REST_API_KEY, process.env.REACT_APP_REDIRECT) }}></img>
                        <div className='login_text'>
                            <span onClick={toggleConditions}>이용약관</span><span>│</span><span onClick={toggleIndividual}>개인정보처리방침</span><span>│</span><a className='login_mailto' href='mailto:blue_notion@naver.com'><span>ContactUs</span></a>
                        </div>
                        <p className='login_name'>©소소한프로젝트</p>
                    </div>
                </div>
            </div>
        </React.Fragment>
    );
};

export default Login;