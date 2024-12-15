import React from 'react';
import { LearningRecord } from '../types/LearningRecord';

interface Props {
    records: LearningRecord[];
    onDelete?: (id: number) => void;
}

const LearningRecordList: React.FC<Props> = ({ records, onDelete }) => {
    return (
        <div className="mt-8">
            <h2 className="text-2xl font-bold mb-4">学習記録一覧</h2>
            <div className="space-y-4">
                {records.map((record) => (
                    <div key={record.id} className="border p-4 rounded shadow">
                        <h3 className="text-xl font-semibold">{record.title}</h3>
                        <p className="mt-2">{record.content}</p>
                        <div className="mt-2 text-gray-600">
                            <span>学習時間: {record.studyMinutes}分</span>
                            <span className="ml-4">
                                記録日時: {new Date(record.createdAt).toLocaleString()}
                            </span>
                        </div>
                        {onDelete && (
                            <button
                                onClick={() => onDelete(record.id)}
                                className="mt-2 text-red-500 hover:text-red-700"
                            >
                                削除
                            </button>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default LearningRecordList; 