
import Card from '../UI/Card';
import classes from './ProductItem.module.css';

// import { useDispatch } from 'react-redux';

const OrderItem = (props) => {

    // const dispatch = useDispatch();

    const { address, orderId, products, totalCost } = props;

    return (
    <li className={classes.item}>
        <Card>
            <header>
                <h3>{address}</h3>
                <div className={classes.price}>{orderId}</div>
            </header>
            {
                products.map( product => (
                    <>
                        <p>Product ID: {product.productId}</p>
                        <p>Quantity: {product.quantity}</p>
                        <p>Quantity: ${+product.price.toFixed(2)}</p>
                        <p>Description: {product.description}</p>
                    </>
                ) )
            }
            <div className={classes.price}>${totalCost.toFixed(2)}</div>
            {/* <div className={classes.actions}>
                <button type='button' >Add to Cart</button>
            </div> */}
        </Card>
    </li>
    );
};

export default OrderItem;
