import AuthAxios from "./AuthAxios";


async function RegisterRequest(dto){
    try{
        const response = await AuthAxios.post('/register',dto);
    return response.data;
    }
    catch(err){
        const resp=err.response;
        if (resp && resp.data && Array.isArray(resp.data.errors)) {
      // Extract validation errors and throw
      const messages = resp.data.errors.map(e => e.message || JSON.stringify(e));
      throw new Error(`Validation failed: ${messages.join(', ')}`);
    }
    if (resp && resp.status) {
      throw new Error(`Server responded with status ${resp.status}`);
    }
    throw err; // network or other unexpected error
    }
}

async function loginRequest(loginDto) {
  try {
    const response = await AuthAxios.post('/login', loginDto);
    return response.data;
  } catch (err) {
    console.error('Failed to Login:', err);
    throw err;
  }
}






export {RegisterRequest,loginRequest}