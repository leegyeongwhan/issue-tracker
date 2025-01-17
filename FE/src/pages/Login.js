import { useEffect, useState, useRef } from 'react';

import { useNavigate } from 'react-router-dom';
import styled, { css } from 'styled-components';

import { Icon } from '../assets/Icon';
import { Button } from '../components/button/Button';
import { TextInput } from '../components/textForm/TextInput';
import { colors } from '../styles/color';
import { fontSize, fontType } from '../styles/font';
import { fetchData } from '../utils/fetch';
export const Login = () => {
  const navigate = useNavigate();

  const idInputRef = useRef(null);
  const pwInputRef = useRef(null);

  const [id, setId] = useState('');
  const [pw, setPw] = useState('');
  const [isLogin, setIsLogin] = useState(true);
  const [user, setUser] = useState([]);

  const focusStyle = isLogin ? null : colors.red;

  const loginUri =
    'https://github.com/login/oauth/authorize?client_id=3b1dfca72b24afb9ebb2&redirect_uri=http://localhost:3000/auth&scope=user';

  const githubLoginHandler = () => {
    window.location.href = loginUri;
  };

  const loginHandler = () => {
    user.forEach((login) => {
      if (login.userId === id && login.userPw === pw) {
        setIsLogin(true);
        navigate('/issues');
      }
    });
    setIsLogin(false);
    idInputRef.current.focus();
    pwInputRef.current.focus();
  };

  const initData = async () => {
    const response = await fetchData('/user');
    setUser(response);
  };

  useEffect(() => {
    initData();
  }, []);

  const logoInfo = {
    iconType: 'logotypeLarge',
    width: 342,
    height: 72
  };

  const loginBtn = {
    size: 'l',
    color: 'containerBlack',
    isIcon: false,
    buttonText: 'GitHub 계정으로 로그인',
    isLeftPosition: true,
    onClick: githubLoginHandler
  };

  const loginInput = [
    {
      id: 1,
      label: '아이디',
      height: '65px',
      value: id,
      setValue: setId,
      loginValue: isLogin,
      myInputRef: idInputRef
    },
    {
      id: 2,
      label: '비밀번호',
      height: '65px',
      value: pw,
      setValue: setPw,
      loginValue: isLogin,
      myInputRef: pwInputRef
    }
  ];

  const idLoginBtn = {
    size: 'l',
    color: 'containerBlue',
    isIcon: false,
    buttonText: '아이디로 로그인',
    disabled: id.length < 1 || pw.length < 1,
    isLeftPosition: true,
    onClick: loginHandler
  };

  const registerBtn = {
    size: 's',
    iconType: 'plus',
    color: 'ghostBlack',
    isIcon: true,
    buttonText: '회원가입',
    isLeftPosition: true
  };

  return (
    <MyLogin>
      <Icon {...logoInfo} />
      <Button {...loginBtn} />
      <span>or</span>
      {loginInput.map((login) => (
        <TextInput
          key={login.id}
          label={login.label}
          height={login.height}
          value={login.value}
          setValue={login.setValue}
          myInputRef={login.myInputRef}
          focusStyle={focusStyle}
        />
      ))}
      <Button {...idLoginBtn} />
      <Button {...registerBtn} />
    </MyLogin>
  );
};

const MyLogin = styled.form`
  gap: 25px;
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  align-items: center;
  width: 320px;

  > svg {
    margin: 50px;
  }

  &:not(:last-child) {
    ${fontSize.L};
    ${fontType.BOLD};
  }
`;
