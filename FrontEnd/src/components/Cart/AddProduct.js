
import { useState } from 'react';
import classes from './AddProbuct.module.css';
import { useDispatch } from 'react-redux';
import insertProduct from '../../functions/InsertProduct';

const AddProduct = () =>
{ 
    
    const dispatch = useDispatch();

    const [ title, setTitle ] = useState('');
    const [ category, setCategory ] = useState('');
    const [ price, setPrice ] = useState('');
    const [ size, setSize ] = useState('');
    const [ color, setColor ] = useState('');
    const [ imagePath, setImagePath ] = useState('');
    const [ description, setDescription ] = useState('');

    const titleChange = (event) =>{
        setTitle(event.target.value);
    }

    const categoryChange = (event) =>{
        setCategory(event.target.value);
    }

    const priceChange = (event) =>{
        setPrice(event.target.value);
    }

    const sizeChange = (event) =>{
        setSize(event.target.value);
    }

    const colorChange = (event) =>{
        setColor(event.target.value);
    }

    const imagePathChange = (event) =>{
        setImagePath(event.target.value);
    }

    const descriptionChange = (event) =>{
        setDescription(event.target.value);
    }

    const submitHandler = (event) =>
    {
        event.preventDefault();
        let product = {
            title,
            category,
            price,
            size,
            color,
            imagePath,
            description
        }
        insertProduct(dispatch, product)
        setTitle('');
        setCategory('');
        setPrice('');
        setSize('');
        setColor('');
        setImagePath('');
        setDescription('');
    }

    return (
        <form className={classes.form}>
          <label >Product Title</label>
          <input type='text' value={title} onChange={titleChange} />
          <label>Category</label>
          <input type='text' value={category} onChange={categoryChange} />
          <label>Price</label>
          <input type='text' value={price} onChange={priceChange} />
          <label>Size</label>
          <input type='text' value={size} onChange={sizeChange} />
          <label>Color</label>
          <input type='text' value={color} onChange={colorChange} />
          <label>Image</label>
          <input type='text' value={imagePath} onChange={imagePathChange} />
          <label>Description</label>
          <input type='text' value={description} onChange={descriptionChange} />
          <button type='button' onClick={submitHandler} >ADD</button>
        </form>
    );
};

export default AddProduct;