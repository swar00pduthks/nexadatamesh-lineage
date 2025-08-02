import React, { useState, useEffect } from 'react';
import { apiService } from '../services/api';
import { BarChart3, RefreshCw } from 'lucide-react';

interface GraphNode {
  id: number;
  label: string;
  type: string;
  namespace: string;
  description: string;
}

interface GraphRelationship {
  source: number;
  target: number;
  type: string;
}

interface GraphData {
  nodes: GraphNode[];
  relationships: GraphRelationship[];
}

export const LineageGraph: React.FC = () => {
  const [graphData, setGraphData] = useState<GraphData | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const loadGraphData = async () => {
    try {
      setLoading(true);
      const response = await fetch('http://localhost:8080/api/graph/lineage');
      if (!response.ok) {
        throw new Error('Failed to load graph data');
      }
      const data = await response.json();
      setGraphData(data);
      setError(null);
    } catch (err) {
      setError('Failed to load lineage graph');
      console.error('Error loading graph:', err);
    } finally {
      setLoading(false);
    }
  };

  const triggerSync = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/graph/sync', {
        method: 'POST',
      });
      if (response.ok) {
        // Reload graph data after sync
        await loadGraphData();
      }
    } catch (err) {
      console.error('Sync failed:', err);
    }
  };

  useEffect(() => {
    loadGraphData();
  }, []);

  if (loading) {
    return (
      <div className="flex items-center justify-center p-8">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-gray-700 dark:border-gray-300"></div>
        <span className="ml-2 text-gray-600 dark:text-gray-400">Loading lineage graph...</span>
      </div>
    );
  }

  if (error) {
    return (
      <div className="bg-red-50 dark:bg-red-900/20 border border-red-200 dark:border-red-800 rounded-lg p-4">
        <p className="text-red-600 dark:text-red-400">{error}</p>
        <button 
          onClick={loadGraphData}
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
        <h2 className="text-2xl font-bold text-gray-900 dark:text-white">Lineage Graph</h2>
        <div className="flex space-x-2">
          <button 
            onClick={triggerSync}
            className="flex items-center px-4 py-2 bg-gray-900 dark:bg-white text-white dark:text-gray-900 rounded-lg hover:bg-gray-800 dark:hover:bg-gray-100 transition-colors"
          >
            <RefreshCw className="w-4 h-4 mr-2" />
            Sync Graph
          </button>
        </div>
      </div>

      {!graphData || graphData.nodes.length === 0 ? (
        <div className="text-center py-12">
          <BarChart3 className="w-12 h-12 text-gray-400 mx-auto mb-4" />
          <h3 className="text-lg font-medium text-gray-900 dark:text-white mb-2">No graph data available</h3>
          <p className="text-gray-500 dark:text-gray-400">
            Create some namespaces and datasets to see the lineage graph.
          </p>
        </div>
      ) : (
        <div className="space-y-6">
          {/* Graph Statistics */}
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div className="bg-white dark:bg-gray-800 p-4 rounded-lg border border-gray-200 dark:border-gray-700">
              <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Nodes</h3>
              <p className="text-2xl font-bold text-gray-700 dark:text-gray-300">
                {graphData.nodes.length}
              </p>
            </div>
            <div className="bg-white dark:bg-gray-800 p-4 rounded-lg border border-gray-200 dark:border-gray-700">
              <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Relationships</h3>
              <p className="text-2xl font-bold text-gray-700 dark:text-gray-300">
                {graphData.relationships.length}
              </p>
            </div>
            <div className="bg-white dark:bg-gray-800 p-4 rounded-lg border border-gray-200 dark:border-gray-700">
              <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Namespaces</h3>
              <p className="text-2xl font-bold text-gray-700 dark:text-gray-300">
                {graphData.nodes.filter(n => n.type === 'Namespace').length}
              </p>
            </div>
          </div>

          {/* Graph Data Table */}
          <div className="bg-white dark:bg-gray-800 rounded-lg border border-gray-200 dark:border-gray-700 overflow-hidden">
            <div className="px-6 py-4 border-b border-gray-200 dark:border-gray-700">
              <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Graph Nodes</h3>
            </div>
            <div className="overflow-x-auto">
              <table className="w-full">
                <thead className="bg-gray-50 dark:bg-gray-700">
                  <tr>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                      Type
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                      Name
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                      Namespace
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                      Description
                    </th>
                  </tr>
                </thead>
                <tbody className="bg-white dark:bg-gray-800 divide-y divide-gray-200 dark:divide-gray-700">
                  {graphData.nodes.map((node) => (
                    <tr key={node.id} className="hover:bg-gray-50 dark:hover:bg-gray-700">
                      <td className="px-6 py-4 whitespace-nowrap">
                        <span className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${
                          node.type === 'Namespace' 
                            ? 'bg-blue-100 text-blue-800 dark:bg-blue-900 dark:text-blue-200'
                            : 'bg-green-100 text-green-800 dark:bg-green-900 dark:text-green-200'
                        }`}>
                          {node.type}
                        </span>
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900 dark:text-white">
                        {node.label}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 dark:text-gray-400">
                        {node.namespace}
                      </td>
                      <td className="px-6 py-4 text-sm text-gray-500 dark:text-gray-400">
                        {node.description}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>

          {/* Relationships */}
          {graphData.relationships.length > 0 && (
            <div className="bg-white dark:bg-gray-800 rounded-lg border border-gray-200 dark:border-gray-700 overflow-hidden">
              <div className="px-6 py-4 border-b border-gray-200 dark:border-gray-700">
                <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Relationships</h3>
              </div>
              <div className="p-6">
                <div className="space-y-2">
                  {graphData.relationships.map((rel, index) => (
                    <div key={index} className="text-sm text-gray-600 dark:text-gray-400">
                      <span className="font-medium">Dataset</span> 
                      <span className="mx-2">→</span>
                      <span className="font-medium">{rel.type}</span>
                      <span className="mx-2">→</span>
                      <span className="font-medium">Namespace</span>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          )}
        </div>
      )}
    </div>
  );
}; 