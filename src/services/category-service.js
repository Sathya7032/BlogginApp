import {myaxios} from './helper';

function loadAllCategories() {
    
    return myaxios.get(`/category/`).then(response => { return response.data });
}
 export default loadAllCategories