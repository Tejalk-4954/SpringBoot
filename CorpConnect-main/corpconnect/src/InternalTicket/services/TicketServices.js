import TicketAxios from "../../Axios/TicketAxios";
import UserAxios from "../../Axios/UserAxios";

export const createTicket = async (data) => {
    try {
        console.log("frontend token", localStorage.getItem("accessToken"));
        const response = await TicketAxios.post("/", data);
        return response.data;
    } catch (error) {
        console.error("Error creating ticket:", error);
        throw error;
    }
};

export const getMyTickets = async (token) => {
  return await TicketAxios.get(`/my`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

export const getTicketsByDepartment = async (departmentId) => {
  try {
    const response = await TicketAxios.get(`/dept`, {
      params: { departmentId },
    });

    return response.data; // list of TicketResponse
  } catch (error) {
    console.error("Error fetching department tickets:", error);
    throw error;
  }
};

export const getEmployeesByDepartment = async (departmentId) => {
  try {
    const response = await UserAxios.get(`/department/${departmentId}/employees`);
    return response.data; // returns list of employees
  } catch (err) {
    console.error("Error fetching employees:", err);
    throw err;
  }
};

export const assignTicket = async (ticketId, userId) => {
  try {
    const body = {
      assignedTo: userId, // This represents AssignTicketRequest DTO
    };

    await TicketAxios.post(`/${ticketId}/assign`, body);
  } catch (err) {
    console.error("Error assigning ticket:", err);
    throw err;
  }
};

export const fetchAssignedTickets = async () => {
  try {
    const token = localStorage.getItem('accessToken'); // Get JWT
    const response = await TicketAxios.get('/assigned', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data; // return the ticket list
  } catch (error) {
    console.error('Error fetching assigned tickets:', error);
    return []; // return empty array on error
  }
};

 
export const closeTicket = async (id) => {
  try {
    const token = localStorage.getItem('accessToken');
    const response = await TicketAxios.post(`/${id}/close`);
    return response.data;
  } catch (error) {
    console.error("Error closing ticket:", error);
    throw error;
  }
};
 

