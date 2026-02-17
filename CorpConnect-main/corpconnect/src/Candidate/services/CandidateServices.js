import axios from "axios";
import CandidateAxios from "../../Axios/CandidateAuth";

async function CandidateReg(dto){
    try{
        const response = await CandidateAxios.post('/register',dto);
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

async function RegenerateMobileOtpRequest(phone) {
  try {
    const response = await CandidateAxios.post(`/send-otp/phone?phone=${phone}`);
    return response.data;
  } catch (err) {
    console.error('OTP regeneration failed:', err);
    throw err;
  }
}

async function RegenerateEmailOtpRequest(email) {
  try {
    const response = await CandidateAxios.post(`/send-otp/email?email=${email}`);
    return response.data;
  } catch (err) {
    console.error('OTP regeneration failed:', err);
    throw err;
  }
}

export {CandidateReg,RegenerateEmailOtpRequest,RegenerateMobileOtpRequest}