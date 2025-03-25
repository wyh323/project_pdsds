import request from '@/utils/request.js';

export const userLoginPatient = (loginData)=> {
    const params = new URLSearchParams()
    for(let key in loginData){
        params.append(key,loginData[key]);
    }
    return request.post('/patient/login',params)
}

export const userRegisterPatient = (registerData)=> {
    const params = new URLSearchParams()
    for(let key in registerData){
        params.append(key,registerData[key]);
    }
    return request.post('/patient/register',params)
}

export const userLoginDoctor = (loginData)=> {
    const params = new URLSearchParams()
    for(let key in loginData){
        params.append(key,loginData[key]);
    }
    return request.post('/doctor/login',params)
}

export const userRegisterDoctor = (registerData)=> {
    const params = new URLSearchParams()
    for(let key in registerData){
        params.append(key,registerData[key]);
    }
    return request.post('/doctor/register',params)
}

export const userLoginAdm = (loginData)=> {
    const params = new URLSearchParams()
    for(let key in loginData){
        params.append(key,loginData[key]);
    }
    return request.post('/administrator/login',params)
}

export const patientUpdateService = (userInfoData)=>{
    return request.put('/patient/update',userInfoData)
 }



