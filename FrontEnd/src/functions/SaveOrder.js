import { uiActions } from "../store/ui-slice";

async function saveOrder (dispatch, cart, status, username) 
{
    dispatch(uiActions.showNotification({
        status: 'pending',
        title: 'Sending...',
        message: 'Sending cart data.'
    }));

    cart['status'] = status;
    cart['username'] = username;

    const sendRequest = async () => {
        const response = await fetch("http://192.168.2.101:8080/api/orders", {
            method: "POST",
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(cart),
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
            message: 'Sent cart data successfully!'
        }));
        
    }
    catch(error)
    {
        dispatch(uiActions.showNotification({
            status: 'error',
            title: 'Error!',
            message: 'Sending cart data failed!'
        }));
    }
}

export default saveOrder;