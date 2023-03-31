import classes from './CartButton.module.css';

import { uiActions } from '../../store/ui-slice';
import { useDispatch, useSelector } from 'react-redux';

const CartButton = (props) => {

  const dispatch = useDispatch();

  const login = useSelector(state => state.login);

  const cartQuantity = useSelector(state => state.cart.totalQuantity);

  const toggleCartHandler = () => {
    dispatch(uiActions.toggle());
  };

  return (
    <button className={classes.button} onClick={toggleCartHandler}>
      <span>{ ( login.username === 'admin' ? 'Add Product' : 'My Cart' ) }</span>
      { ( login.username === 'user' ) && <span className={classes.badge}>{cartQuantity}</span>}
    </button>
  );
};

export default CartButton;
