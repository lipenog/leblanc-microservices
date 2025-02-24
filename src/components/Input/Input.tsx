import { KeyboardEventHandler, useState } from 'react';
import styles from './Input.module.css'
import { CiSearch } from 'react-icons/ci';
import { useNavigate } from 'react-router-dom';


function SearchInput() {
    const navigate = useNavigate();
    const [value, setValue] = useState('')
    
    const navigateToSearchResult = () => {
        navigate(`/search?q=${value}`)
    }

    const handleEnter = (e : React.KeyboardEvent<HTMLInputElement>) => {
        if(e.key === 'Enter') {
            navigateToSearchResult();
        }
    }

    return ( 
        <div className={styles.inputShape}>
            <input placeholder={'search'}
                className={styles.input}
                onChange={(e) => {setValue(e.target.value)}}
                onKeyDown={handleEnter}
                /> 
            <CiSearch onClick={navigateToSearchResult}/>
        </div>
     );
}

export default SearchInput;