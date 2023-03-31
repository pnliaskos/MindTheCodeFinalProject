import { useDispatch, useSelector } from 'react-redux';
import ProductItem from './ProductItem';
import classes from './Products.module.css';
import fetchProducts from '../../functions/FetchProducts';

let i = 0;

const Products = (props) => {

  const dispatch = useDispatch();

  const products = useSelector(state => state.products.items);

  const pages = useSelector( state => state.products.pages );

  const login = useSelector( state => state.login );

  const onPlus = () => {
    if( i < pages - 1 )
    {
      i++;
      fetchProducts(dispatch, i);
    }
  }
  
  const onMinus = () => {
    if( i > 0 )
    {
      i--;
      fetchProducts(dispatch, i);
    }
  }

  return (
    <section className={classes.products}>
      <h2>{ ( login.username === 'user' ? 'Buy your favorite products' : 'ADMIN MODE' ) }</h2>
      <ul> 
        {products.map(product => (
          <ProductItem
            key={product.id}
            id={product.id}
            title={product.title}
            price={product.price}
            category={product.category}
            imagePath={product.imagePath}
            description={product.description}
          />
        ))}
      </ul>
      <div className={classes.paginationButtons}>
        <button type='button' onClick={onMinus} >{'<'}</button>
        <span>{i + 1}</span>
        <button type='button' onClick={onPlus} >{'>'}</button>
      </div>
    </section>
  );
};

export default Products;
