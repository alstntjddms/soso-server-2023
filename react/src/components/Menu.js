import React, { useState } from 'react';
import './Menu.css';

function Menu(props) {

    const [isMenu, setIsMenu] = useState(false);
    const [isInner, setIsInner] = useState(false);
    const [isMypage, setIsMypage] = useState(false);
    const [isPlanetter, setIsPlanetter] = useState(false);
    const [isHowto, setIsHowto] = useState(false);
    const [membershipWithdrawal, setMembershipWithdrawal] = useState(false);

    function MembershipWithdrawal() {
        return (
            <div className={membershipWithdrawal ? "membershipWithdrawal" : "membershipWithdrawal_fade"}>
                <div className='membershipWithdrawal_outContainer'>
                    <p className='membershipWithdrawal_title'>서비스를</p>
                    <p className='membershipWithdrawal_title'>탈퇴하시겠습니까?</p>
                    <p className='membershipWithdrawal_p'>탈퇴 시 그동안 저장된 데이터는 모두 삭제됩니다.</p>
                    <span className='membershipWithdrawal_HLine'></span>
                    <span className='membershipWithdrawal_VLine'></span>
                    <div className='membershipWithdrawal_innerBox'>
                        <div className='membershipWithdrawal_button' onClick={() => { alert('아직 서비스 준비 중입니다.') }}>탈퇴</div>
                        <div className='membershipWithdrawal_button' onClick={() => { setMembershipWithdrawal(!membershipWithdrawal) }}>취소</div>
                    </div>
                </div>
            </div>
        );
    };

    function toggleMenu() {
        if (isInner === false) {
            setIsMenu(!isMenu);
            setIsInner(false);
            setIsMypage(false);
            setIsPlanetter(false);
            setIsHowto(false);
        } else if (isInner === true) {
            setIsInner(false);
            setIsMypage(false);
            setIsPlanetter(false);
            setIsHowto(false);
        };
    };

    function toggleMypage() {
        setIsInner(!isInner);
        setIsMypage(!isMypage);
    };

    function togglePlanetter() {
        setIsInner(!isInner);
        setIsPlanetter(!isPlanetter);
    };

    function toggleHowto() {
        setIsInner(!isInner);
        setIsHowto(!isHowto);
    };

    function logoutWithKakao(key, url) {
        window.location.href = `https://kauth.kakao.com/oauth/logout?client_id=${key}&logout_redirect_uri=${url}`;
    };

    return (
        <React.Fragment>
            <div className={isMenu ? "menu_wrap" : ""}>
                <MembershipWithdrawal></MembershipWithdrawal>
                <div className="menu_outContainer">
                    <div className={`menu_img${isMenu ? "_active" : ""}${isMypage || isPlanetter || isHowto ? "_plus" : ""}`} onClick={toggleMenu}></div>
                </div>
                <div className={`menu_bar${isMenu ? "_active" : ""}${isInner ? "_wide" : ""}`}>
                    <div className={isInner ? "menu_bar_inner_true" : "menu_bar_inner"}>
                        <p onClick={toggleMypage}>마이페이지</p>
                        <p onClick={togglePlanetter}>Planetter</p>
                        <p onClick={toggleHowto} >이용 방법</p>
                        <p>Contact Us</p>
                    </div>
                    <div className={isMypage ? "menu_bar_mypage" : "menu_bar_mypage_true"}>
                        <div className='menu_bar_mypage_box1'>
                            <p className='menu_bar_mypage_box1_p'>안녕하세요!</p>
                            <div></div>
                            <p className='menu_bar_mypage_box1_p'>{props.props.nickname} 님</p>
                            <div className='menu_bar_mypage_box1_innerBox'>
                                <div className='menu_bar_mypage_box1_logout' onClick={() => { logoutWithKakao(process.env.REACT_APP_REST_API_KEY, process.env.REACT_APP_REDIRECT2) }}>로그아웃</div>
                            </div>
                        </div>
                        <div className='menu_bar_mypage_line1'></div>
                        <div className='menu_bar_mypage_box2'>
                            <p className='menu_bar_mypage_box2_p'>카카오톡 알림</p>
                            <div className='menu_bar_mypage_box2_innerBox'>
                                <div className='menu_bar_mypage_box2_notice' onClick={() => { alert('아직 서비스 준비 중입니다.') }}><div className='menu_bar_mypage_box2_notice_inner'></div></div>
                            </div>
                        </div>
                        <div className='menu_bar_mypage_line2'></div>
                        <p className='menu_bar_mypage_box_p' onClick={() => { setMembershipWithdrawal(!membershipWithdrawal) }}>회원탈퇴</p>
                        <div className='menu_bar_mypage_line'></div>
                        <p className='menu_bar_mypage_box_p'>서비스 이용 약관</p>
                        <div className='menu_bar_mypage_line'></div>
                        <p className='menu_bar_mypage_box_p'>개인정보 처리 방침</p>
                        <div className='menu_bar_mypage_line'></div>
                        <p className='menu_bar_mypage_box_p'>문의하기</p>
                    </div>
                    <div className={isPlanetter ? "menu_bar_planetter" : "menu_bar_planetter_true"}>
                        <p>Planetter</p>
                    </div>
                    <div className={isHowto ? "menu_bar_howto" : "menu_bar_howto_true"}>
                        <p>이용방법</p>
                    </div>
                </div>
            </div>
        </React.Fragment>
    );
};

export default Menu;