import { createRouter, createWebHistory } from 'vue-router'

import LoginVue from '@/views/Login.vue'
import DoctorMainVue from '@/views/DoctorMain.vue'
import PatientMainVue from '@/views/PatientMain.vue'
import AdminMainVue from '@/views/AdminMain.vue'
import SdsPost from '@/views/SdsPost.vue'
import MadrsPost from '@/views/MadrsPost.vue'
import CesdPost from '@/views/CesdPost.vue'
import PatientUpdate from '@/views/PatientUpdate.vue'
import ViewPatientInfo from '@/views/ViewPatientInfo.vue'
import ViewPatientDetail from '@/views/ViewPatientDetail.vue'
import AdminDoctorOptionsVue from '@/views/AdminDoctorOptions.vue'
import AdminPatientOptionsVue from '@/views/AdminPatientOptions.vue'
const routes = [
    { path: '/login', component: LoginVue },
    { path: '/doctormain', component: DoctorMainVue ,redirect:'/doctormain/viewpatientinfo', children: [
        { path: 'viewpatientinfo', component: ViewPatientInfo },
        { path: 'viewpatientdetail', component:ViewPatientDetail}
    ]},
    { path: '/patientmain', component: PatientMainVue,redirect:'/patientmain/sdspost', children: [
        { path: 'sdspost', component: SdsPost },
        { path: 'madrspost', component: MadrsPost },
        { path: 'cesdpost', component: CesdPost },
        { path: 'update', component: PatientUpdate}
    ] },
    { path: '/adminmain', component: AdminMainVue, redirect:'/adminmain/admindoctoroptions', children:[
        { path: 'admindoctoroptions', component: AdminDoctorOptionsVue },
        { path: 'adminpatientoptions', component: AdminPatientOptionsVue }
    ]}
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

export default router