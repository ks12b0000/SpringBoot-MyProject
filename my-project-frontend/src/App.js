import React from 'react';
import { Container } from 'react-bootstrap';
import { Route } from 'react-router-dom';
import Header from './components/Header';
import JoinForm from './pages/user/JoinForm';
import LoginForm from './pages/user/LoginForm';

function App() {
  return (
    <div>
      <Header />
      <Container>
        <Route path="/" exact={true} component={''} />
        <Route path="/joinForm" exact={true} component={JoinForm} />
        <Route path="/loginForm" exact={true} component={LoginForm} />
      </Container>
    </div>
  );
}

export default App;
