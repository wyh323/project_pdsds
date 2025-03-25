    import request from '@/utils/request.js'; 

    export const getAllDoctorInfo = () => {  
        return request.get('/administrator/allDoctorInfo'); 
    };  

    export const deleteDoctor = (username) => {  
        return request.delete(`/administrator/deleteDoctor?username=${username}`); 
    };

    export const getAllPatientInfo = () => {  
        return request.get('/administrator/allPatientInfo'); 
    }