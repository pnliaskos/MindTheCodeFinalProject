import { contentActions } from '../../store/main-content-slice';
import classes from './CartButton.module.css';

import { useDispatch } from 'react-redux';

const ProductsButton = (props) => {

    const dispatch = useDispatch();

    const toggleOrders = () => {
        dispatch( contentActions.toggle('products') )
    };

    return (
        <button className={classes.button} onClick={toggleOrders}>
        <span>Probucts</span>
        </button>
    );
};

export default ProductsButton;
