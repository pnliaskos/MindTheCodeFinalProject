import Cart from "./components/Cart/Cart";
import Layout from "./components/Layout/Layout";
import Products from "./components/Shop/Products";

import { useDispatch, useSelector } from "react-redux";
import { Fragment } from "react";
import Notification from './components/UI/Notification';
import Login from "./components/login/Login";
import Orders from "./components/Shop/Orders";
import fetchProducts from "./functions/FetchProducts";
import { useEffect } from "react";
import fetchOrders from "./functions/FetchOrders";
import { cartActions } from "./store/cart-slice";
// import testfetch from "./functions/test";

function App() {

  const dispatch = useDispatch();

  const orders = useSelector( state => state.orders.items );
  
  const login = useSelector(state => state.login);
  
  let existingCart = [];
  for( let item of orders )
  {
    if( item.username === login.username && item.status === 'cart' )
    { 
      existingCart = item.products;
    }

    for( let item of existingCart )
    {
      item['totalPrice'] = item.price * item.quantity;
    }
  }

  dispatch( cartActions.replaceCart({items: existingCart}) );

  const showCart = useSelector((state) => state.ui.cartIsVisible);

  const notification = useSelector(state => state.ui.notification);

  const content = useSelector( state => state.content.content ); 
  

  useEffect( () => {
    fetchProducts(dispatch, 0);
    fetchOrders(dispatch);
    // console.log(testfetch());
  }, [dispatch])

  return (
    <Fragment>
      { notification && <Notification status={notification.status} title={notification.title} message={notification.message} /> }

      {( !login.status ) && <Login />}

      {
        ( login.status && (login.username === 'user1' || login.username === 'user2' )) &&
        <Layout>
          {showCart && <Cart />}
          <Products />
          
        </Layout>
      }

      {
        ( login.status && login.username === 'admin') &&
        <Layout>
          {showCart && <Cart />}

          { content === 'products' ? <Products /> : <Orders />}
          
        </Layout>
      }
    </Fragment>
  );
}

export default App;
