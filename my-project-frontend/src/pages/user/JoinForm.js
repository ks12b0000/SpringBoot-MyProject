import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import { useState } from 'react';

const JoinForm = (props) => {
  const [join, setJoin] = useState({
    username: '',
    name: '',
    email: '',
    password: '',
  });

  const changeValue = (e) => {
    setJoin({
      ...join,
      [e.target.name]: e.target.value,
    });
  };

  const submitJoin = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/joinForm', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      },
      body: JSON.stringify(join),
    })
      .then((res) => {
        if (res.status === 201) {
          return res.json();
        } else {
          return null;
        }
      })
      .then((res) => {
        if (res !== null) {
          alert('회원가입이 완료되었습니다.');
          props.history.push('/');
        } else {
          alert('회원가입 실패!');
        }
      });
  };
  return (
    <div>
      <Container className="panel">
        <h1>회원 가입</h1>
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
            <Col sm>
              <label>이름</label>
              <Form.Control
                type="text"
                placeholder="이름을 입력해주세요."
                onChange={changeValue}
                name="name"
              />
            </Col>
          </Form.Group>

          <Form.Group as={Row} className="mb-3" controlId="formBasicEmail">
            <Col sm>
              <label>이메일</label>
              <Form.Control
                type="email"
                placeholder="이메일을 입력해주세요."
                onChange={changeValue}
                name="email"
              />
            </Col>
          </Form.Group>
          <br />

          <div className="d-grid gap-1">
            <Button variant="secondary" type="submit">
              Sign Up
            </Button>
          </div>
        </Form>
      </Container>
    </div>
  );
};

export default JoinForm;
