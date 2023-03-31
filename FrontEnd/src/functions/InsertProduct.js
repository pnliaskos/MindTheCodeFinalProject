import { uiActions } from "../store/ui-slice";
import fetchProducts from "./FetchProducts";

async function insertProduct (dispatch, product) 
{
    dispatch(uiActions.showNotification({
        status: 'pending',
        title: 'Sending...',
        message: 'Sending product data.'
    }));

    const sendRequest = async () => {
        const response = await fetch("http://192.168.2.101:8080/api/products", {
            method: "POST",
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(product),
        });

        if( !response.ok )
        {
            throw new Error('Sending cart data failed.');
        }
    };

    try
    {
        await sendRequest();
        dispatch(uiActions.showNotification({
            status: 'success',
            title: 'Success!',
            message: 'Sent product data successfully!'
        }));
        fetchProducts(dispatch);
        dispatch(uiActions.toggle());
    }
    catch(error)
    {
        dispatch(uiActions.showNotification({
            status: 'error',
            title: 'Error!',
            message: 'Sending product data failed!'
        }));
    }
}

export default insertProduct;