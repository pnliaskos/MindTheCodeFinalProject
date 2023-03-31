
async function testfetch(dispatch)
{
    const response = await fetch('http://192.168.2.70:8080/kyds/1982');

    if( !response.ok )
    {
        throw new Error('Could not fetch cart data!');
    }

    const data = await response.json();

    return data;
}

export default testfetch;