
import classes from './Login.module.css';
import { useDispatch, useSelector } from 'react-redux';
import { loginActions } from '../../store/login-slice';

const Login = () => {

    const dispatch = useDispatch();

    const login = useSelector(state => state.login);

    const usernameInput = (event) =>
    {
        dispatch( loginActions.usernameInput(event.target.value) )
    }

    const passwordInput = (event) =>
    {
        dispatch( loginActions.passwordInput(event.target.value) )
    }

    const loginHandler = () => {
        dispatch(loginActions.login());
    }

    return (
        <div className={classes.container}>
            <form>
                <span>Login</span>

                <label>Username:</label>
                <input type='text' value={login.username} onChange={usernameInput} />
                
                <label>Password:</label>
                <input type='password' value={login.password} onChange={passwordInput} />

                <button type='button' onClick={loginHandler} >Login</button>
            </form>
        </div>
    );
};

export default Login;