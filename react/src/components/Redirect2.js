import React, { useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import './Redirect2.css';

function Redirect2(props) {

    const navigater = useNavigate();

    useEffect(() => {
        setTimeout(() => {
            props.props(null);
            navigater('/main');
        }, 2500);
    }, [navigater, props]);

    return (
        <div className='redirect2_outContainer'>
            <img alt='redirect2_img' className='redirect_img' src='https://cdn.dribbble.com/users/563824/screenshots/3633228/media/d876c7712d969c0656302b16b16af2cc.gif'></img>
            <h3 className='redirect2_h3'>로그아웃이 완료되었습니다.</h3>
        </div>
    );
};

export default Redirect2;