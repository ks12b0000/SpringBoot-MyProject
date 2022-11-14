import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import { useState } from 'react';

const LoginForm = (props) => {
  const [login, setLogin] = useState({
    username: '',
    password: '',
    autoLogin: false,
  });

  const changeValue = (e) => {
    setLogin({
      ...login,
      [e.target.name]: e.target.value,
    });
  };

  const submitJoin = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      },
      body: JSON.stringify(login),
    })
      .then((res) => {
        if (res.status === 200) {
          return res.json();
        } else {
          return null;
        }
      })
      .then((res) => {
        if (res !== null) {
          alert('로그인에 성공했습니다.');
          props.history.push('/');
        } else {
          alert('로그인 실패!');
        }
      });
  };
  return (
    <div>
      <Container className="panel">
        <h1>로그인</h1>
        <Form onSubmit={submitJoin}>
          <Form.Group
            as={Row}
            className="mb-3"
            controlId="formPlaintextPassword"
          >
            <Col sm>
              <label>아이디</label>
              <Form.Control
                type="text"
                placeholder="아이디를 입력해주세요."
                onChange={changeValue}
                name="username"
              />
            </Col>
          </Form.Group>

          <Form.Group
            as={Row}
            className="mb-3"
            controlId="formPlaintextPassword"
          >
            <Col sm>
              <label>비밀번호</label>
              <Form.Control
                type="password"
                placeholder="비밀번호를 입력해주세요."
                onChange={changeValue}
                name="password"
              />
            </Col>
          </Form.Group>

          <Form.Group
            as={Row}
            className="mb-3"
            controlId="formPlaintextPassword"
          >
            <Col xs="auto" className="my-1">
              <Form.Check
                type="checkbox"
                id="autoLogin"
                onChange={changeValue}
                label="자동 로그인"
              />
            </Col>
          </Form.Group>
          <br />

          <div className="d-grid gap-1">
            <Button variant="secondary" type="submit">
              Login
            </Button>
          </div>
        </Form>
        
      </Container>
    </div>
  );
};

export default LoginForm;
