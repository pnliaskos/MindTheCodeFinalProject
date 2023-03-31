import { useDispatch, useSelector } from 'react-redux';
import classes from './Products.module.css';
import OrderItem from './OrderItem';
import fetchOrders from '../../functions/FetchOrders';
import { useEffect } from 'react';

const Orders = (props) => {

    const dispatch = useDispatch();

    const orders = useSelector(state => state.orders.items);

    const login = useSelector( state => state.login );

    useEffect( () => {
        fetchOrders(dispatch);
    }, [dispatch])

    return (
    <section className={classes.products}>
        <h2>{ ( login.username === 'user' ? 'Buy your favorite products' : 'ADMIN MODE' ) }</h2>
        <ul> 
        {orders.map(order => (
            <OrderItem
                key={ order.id }
                address={ order.address }
                orderId={ order.id }
                products={ order.products }
                totalCost={ order.totalCost }
            />
        ))}
        </ul>
    </section>
    );
};

export default Orders;
