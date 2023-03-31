import { productsActions } from "../store/products-slice";

async function fetchProducts(dispatch, page)
{
    const response = await fetch(`http://192.168.2.101:8080/api/products?page=${page}`);

    if( !response.ok )
    {
        throw new Error('Could not fetch cart data!');
    }

    const data = await response.json();

    dispatch( productsActions.replaceProducts({content: data.content, pages: data.totalPages}) );

}

export default fetchProducts;