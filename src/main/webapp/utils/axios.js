import axios from 'axios';

const apiUrl = document.getElementById('root').getAttribute('apiurl');
const instance = axios.create({
    baseURL: apiUrl,
});

export default instance;