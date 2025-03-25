import request from '@/utils/request.js';


export const patientSdsPost = (sdsData)=> {
    return request.post('/patient/sds',sdsData)
}

export const patientMadrsPost = (mardsData)=> {
    return request.post('/patient/madrs',mardsData)
}

export const patientCesdPost = (cesdData)=> {
    return request.post('/patient/ces_d',cesdData)
}

export const myPatientInfo = ()=>{
    return request.get('/doctor/myPatientsInfo')
 }

 export const patientDetail = (username)=>{
    return request.get('/doctor/patientDetail?username='+username)
 }