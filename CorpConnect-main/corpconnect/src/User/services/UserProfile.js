import UserAxios from "../../Axios/UserAxios";

async function UserProfile(){
    try{
        const resposne = await UserAxios.get(`/me`);
        return resposne.data;
    }
    catch(err){
        console.error("User profile error",err);
    }
}

export const getDepartmentByUserId = async (userId) => {
  try {
    const response = await UserAxios.get(`/${userId}/department`);
    return response.data; // returns department as string
  } catch (err) {
    console.error("Error fetching department:", err);
    throw err;
  }
};

export const getUserById = async (userId) => {
  try {
    const token  = localStorage.getItem("accessToken");
    const response = await UserAxios.get(`/${userId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data; // returns UserDto
  } catch (err) {
    console.error("Error fetching user by ID:", err);
    throw err;
  }
};

export {UserProfile}