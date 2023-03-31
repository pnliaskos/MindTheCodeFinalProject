import { useDispatch, useSelector } from 'react-redux';
import CartButton from '../Cart/CartButton';
import classes from './MainHeader.module.css';
import { loginActions } from '../../store/login-slice';
import OrdersButton from '../Cart/OrdersButton';
import ProductsButton from '../Cart/ProductsButton';

const MainHeader = (props) => {

  const dispatch = useDispatch();

  const login = useSelector( state => state.login );  

  const logoutHandler = () => {
    dispatch(loginActions.logout());
  }

  return (
    <header className={classes.header}>
      <h1>Fit~It :D</h1>
      <nav>
        <ul>
          { login.username === 'admin' &&
            <li>
              <OrdersButton />
            </li>
          }
          { login.username === 'admin' &&
            <li>
              <ProductsButton />
            </li>
          }
          <li>
            <CartButton />
          </li>
          <li>
            <button type='button' onClick={logoutHandler} >Logout</button>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default MainHeader;
