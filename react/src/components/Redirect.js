import React, { useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import './Redirect.css';

function Redirect(props) {

    const navigater = useNavigate();
    const urlParams = new URL(window.location.href).searchParams;
    const name = urlParams.get('code');
    const urlParamsErro = new URL(window.location.href).searchParams;
    const nameErro = urlParamsErro.get('error_description');

    useEffect(() => {
        if (nameErro === 'User denied access') {
            alert('로그인에 실패하였습니다.');
            navigater('/login');
        };
        const code = { code: name };
        const queryStringBody = Object.keys(code)
            .map(k => encodeURIComponent(k) + "=" + encodeURI(code[k]))
            .join("&");
        fetch("http://13.209.184.10:8080/api/kakao", {
            method: "POST",
            headers: {
                'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
            },
            body: queryStringBody
        })
            .then(res => res.json())
            .then((data) => {
                props.props(data)
                console.log('성공:', data);
                navigater('/main');
            })
            .catch((error) => {
                console.log(error);
                alert('서버가 불안정 하여 로그인에 실패했습니다.');
                navigater('/login');
            });
    }, [name, nameErro, navigater, props]);

    return (
        <div className='redirect_outContainer'>
            <img alt='redirect_img' className='redirect_img' src='https://cdn.dribbble.com/users/563824/screenshots/3633228/media/d876c7712d969c0656302b16b16af2cc.gif'></img>
            <h3 className='redirect_h3'>로그인 중입니다</h3>
        </div>
    );
};

export default Redirect;