import React, { useState, useEffect } from 'react';
import { apiService, Namespace } from '../services/api';
import { Database, Plus, Edit, Trash2 } from 'lucide-react';

export const NamespacesList: React.FC = () => {
  const [namespaces, setNamespaces] = useState<Namespace[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    loadNamespaces();
  }, []);

  const loadNamespaces = async () => {
    try {
      setLoading(true);
      const data = await apiService.getNamespaces();
      setNamespaces(data);
      setError(null);
    } catch (err) {
      setError('Failed to load namespaces');
      console.error('Error loading namespaces:', err);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center p-8">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-gray-700 dark:border-gray-300"></div>
        <span className="ml-2 text-gray-600 dark:text-gray-400">Loading namespaces...</span>
      </div>
    );
  }

  if (error) {
    return (
      <div className="bg-red-50 dark:bg-red-900/20 border border-red-200 dark:border-red-800 rounded-lg p-4">
        <p className="text-red-600 dark:text-red-400">{error}</p>
        <button 
          onClick={loadNamespaces}
          className="mt-2 px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700 transition-colors"
        >
          Retry
        </button>
      </div>
    );
  }

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h2 className="text-2xl font-bold text-gray-900 dark:text-white">Namespaces</h2>
        <button className="flex items-center px-4 py-2 bg-gray-900 dark:bg-white text-white dark:text-gray-900 rounded-lg hover:bg-gray-800 dark:hover:bg-gray-100 transition-colors">
          <Plus className="w-4 h-4 mr-2" />
          Add Namespace
        </button>
      </div>

      {namespaces.length === 0 ? (
        <div className="text-center py-12">
          <Database className="w-12 h-12 text-gray-400 mx-auto mb-4" />
          <h3 className="text-lg font-medium text-gray-900 dark:text-white mb-2">No namespaces found</h3>
          <p className="text-gray-500 dark:text-gray-400">Create your first namespace to get started.</p>
        </div>
      ) : (
        <div className="grid gap-4">
          {namespaces.map((namespace) => (
            <div key={namespace.id} className="bg-white dark:bg-gray-800 border border-gray-200 dark:border-gray-700 rounded-lg p-6 hover:shadow-md transition-shadow">
              <div className="flex items-center justify-between">
                <div className="flex items-center">
                  <Database className="w-6 h-6 text-gray-700 dark:text-gray-300 mr-3" />
                  <div>
                    <h3 className="text-lg font-semibold text-gray-900 dark:text-white">{namespace.name}</h3>
                    <p className="text-gray-600 dark:text-gray-400">{namespace.description}</p>
                    <p className="text-sm text-gray-500 dark:text-gray-500">Owner: {namespace.owner}</p>
                  </div>
                </div>
                <div className="flex space-x-2">
                  <button className="p-2 text-gray-400 hover:text-gray-700 dark:hover:text-gray-200 transition-colors">
                    <Edit className="w-4 h-4" />
                  </button>
                  <button className="p-2 text-gray-400 hover:text-red-600 dark:hover:text-red-400 transition-colors">
                    <Trash2 className="w-4 h-4" />
                  </button>
                </div>
              </div>
              <div className="mt-4 text-xs text-gray-500 dark:text-gray-400">
                Created: {new Date(namespace.createdAt).toLocaleDateString()}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}; 