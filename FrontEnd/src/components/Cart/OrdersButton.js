import { contentActions } from '../../store/main-content-slice';
import classes from './CartButton.module.css';

import { useDispatch } from 'react-redux';

const OrdersButton = (props) => {

    const dispatch = useDispatch();

    const toggleOrders = () => {
        dispatch( contentActions.toggle('orders') )
    };

    return (
        <button className={classes.button} onClick={toggleOrders}>
        <span>Orders</span>
        </button>
    );
};

export default OrdersButton;
