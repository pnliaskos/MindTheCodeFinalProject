
import Card from '../UI/Card';
import classes from './Cart.module.css';
import CartItem from './CartItem';
import saveOrder from '../../functions/SaveOrder'

import { useDispatch, useSelector } from 'react-redux';
import AddProduct from './AddProduct';

const Cart = (props) => {

  const dispatch = useDispatch()

  const cartItems = useSelector(state => state.cart.items);

  const login = useSelector( state => state.login );

  const orderToSave = { address: 'Some Adress', products: [] };

  for( let item of cartItems )
  {
    orderToSave.products.push( { productId: item.id, quantity: item.quantity} )
  }

  const saveOrderHandler = () => {
    let status = 'cart'
    saveOrder(dispatch, orderToSave, status, login.username);
  }
  
  const checkoutOrderHandler = () => {
    let status = 'submitted'
    saveOrder(dispatch, orderToSave, status, login.username);
  }
  
  let content;
  switch( login.username )
  {
    case 'user1': case 'user2' :
      content = <>
        <h2>Your Shopping Cart</h2>
        <ul>
          {cartItems.map(item => (
            <CartItem
              key={item.id}
              item={{
                id: item.id,
                title: item.title, 
                quantity: item.quantity, 
                total: item.totalPrice, 
                price: item.price 
              }}
            />
          ))}
        </ul>
        <button type='button' onClick={saveOrderHandler} >Save Order</button>
        <button type='button' onClick={checkoutOrderHandler} >Checkout</button>
      </>
      break;
    case 'admin':
      content = <AddProduct />
      break;
    default:
      break;
  }

  return (
    <>
    <Card className={classes.cart}>
      {content}
    </Card>
    </>
  );
};

export default Cart;
