import { ordersActions } from "../store/orders.slice";



async function fetchOrders(dispatch)
{
    const response = await fetch('http://192.168.2.101:8080/api/orders?size=500');

    if( !response.ok )
    {
        throw new Error('Could not fetch orders data!');
    }

    const data = await response.json();

    dispatch( ordersActions.replaceProducts(data.content) )
}

export default fetchOrders;