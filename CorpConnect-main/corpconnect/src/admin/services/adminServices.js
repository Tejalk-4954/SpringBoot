import UserAxios from "../../Axios/UserAxios";

async function fetchUserList(){
    try{
        const resposne = await UserAxios.get(`/all`);
        return resposne.data;
    }
    catch(err){
        console.error("User profile error",err);
    }
}

async function AssignRole(id,role){
    try{
        const resposne = await UserAxios.post(`/${id}/roles`, { roles : [role] });
        return resposne.data;
    }
    catch(err){
        console.error("User profile error",err);
    }
}

async function AssignDepartment(id,dept){
    try{
        const resposne = await UserAxios.post(`/${id}/department`, { department : dept });
        return resposne.data;
    }
    catch(err){
        console.error("User profile error",err);
    }
}

export {fetchUserList,AssignRole,AssignDepartment}