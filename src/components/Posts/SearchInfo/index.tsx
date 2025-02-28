import styles from './SearchInfo.module.css'

interface Props {
    query : string | null;
}

function SearchInfo({query} : Props) {
    return ( 
        <div className={styles.searchInfo}>
            <div className={styles.searchInfoQuery}>
                <span>Exibindo resultados de:</span>
                <span className={styles.textInfo}>{query}</span>            
            </div>
        </div>
    );
}

export default SearchInfo;