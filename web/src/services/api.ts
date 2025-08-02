import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export interface Namespace {
  id: number;
  name: string;
  description: string;
  owner: string;
  createdAt: string;
  updatedAt?: string;
}

export interface Dataset {
  id: number;
  name: string;
  namespace: string;
  description: string;
  type: string;
  location: string;
  createdAt: string;
  updatedAt?: string;
}

export const apiService = {
  // Namespace endpoints
  getNamespaces: async (): Promise<Namespace[]> => {
    const response = await api.get('/namespaces');
    return response.data;
  },

  getNamespaceById: async (id: number): Promise<Namespace> => {
    const response = await api.get(`/namespaces/${id}`);
    return response.data;
  },

  createNamespace: async (namespace: Omit<Namespace, 'id' | 'createdAt'>): Promise<Namespace> => {
    const response = await api.post('/namespaces', namespace);
    return response.data;
  },

  updateNamespace: async (id: number, namespace: Partial<Namespace>): Promise<Namespace> => {
    const response = await api.put(`/namespaces/${id}`, namespace);
    return response.data;
  },

  deleteNamespace: async (id: number): Promise<void> => {
    await api.delete(`/namespaces/${id}`);
  },

  // Dataset endpoints
  getDatasets: async (): Promise<Dataset[]> => {
    const response = await api.get('/datasets');
    return response.data;
  },

  getDatasetsByNamespace: async (namespace: string): Promise<Dataset[]> => {
    const response = await api.get(`/datasets/namespace/${namespace}`);
    return response.data;
  },

  getDatasetById: async (id: number): Promise<Dataset> => {
    const response = await api.get(`/datasets/${id}`);
    return response.data;
  },

  createDataset: async (dataset: Omit<Dataset, 'id' | 'createdAt'>): Promise<Dataset> => {
    const response = await api.post('/datasets', dataset);
    return response.data;
  },

  updateDataset: async (id: number, dataset: Partial<Dataset>): Promise<Dataset> => {
    const response = await api.put(`/datasets/${id}`, dataset);
    return response.data;
  },

  deleteDataset: async (id: number): Promise<void> => {
    await api.delete(`/datasets/${id}`);
  },

  // Health check
  healthCheck: async (): Promise<string> => {
    const response = await api.get('/health');
    return response.data;
  },
}; 