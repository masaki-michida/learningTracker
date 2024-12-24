import React, { useState, useEffect } from 'react';
import LearningRecordForm from '../components/LearningRecordForm';
import LearningRecordList from '../components/LearningRecordList';
import { LearningRecord } from '../types/LearningRecord';
import { useAuth } from '../contexts/AuthContext';


const LearningRecordPage: React.FC = () => {
    const { logout } = useAuth();
    const [records, setRecords] = useState<LearningRecord[]>([]);

    // 学習記録を取得する関数
    const fetchRecords = async () => {
        try {
            
            const token = localStorage.getItem('token'); // トークンを取得
            const response = await fetch(`http://localhost:8080/api/learning-records`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}` // Authorizationヘッダーを設定
                }
            });

            if (response.ok) {
                const data = await response.json();
                setRecords(data);
            } else {
                console.error('Failed to fetch records:', response.statusText);
            }
        } catch (error) {
            console.error('Error fetching records:', error);
        }
    };

    // 学習記録を削除する関数
    const handleDelete = async (id: number) => {
        try {
            const token = localStorage.getItem('token');
            const response = await fetch(`http://localhost:8080/api/learning-records/${id}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`  // トークンを追加
                }
            });
            if (response.ok) {
                setRecords(records.filter(record => record.id !== id));
            }
        } catch (error) {
            console.error('Error deleting record:', error);
        }
    };

    // コンポーネントマウント時に記録を取得
    useEffect(() => {        
            fetchRecords();
    }, []);

    return (
        <div className="container mx-auto px-4">
            <div className="flex justify-between items-center mb-8">
                <h1 className="text-3xl font-bold">学習記録</h1>
                <button
                    onClick={logout}
                    className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                >
                    ログアウト
                </button>
            </div>
            <LearningRecordForm onRecordSubmit={fetchRecords} />
            <LearningRecordList records={records} onDelete={handleDelete} />
        </div>
    );
};

export default LearningRecordPage;