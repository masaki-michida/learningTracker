// src/components/LearningRecordForm.tsx

import React, { useState } from 'react';

interface LearningRecordInput {
  title: string;
  content: string;
  studyMinutes: number;
}

const LearningRecordForm: React.FC = () => {
    const [formData, setFormData] = useState<LearningRecordInput>({
        title: '',
        content: '',
        studyMinutes: 0
    });

    // 入力変更時のハンドラー
    const handleChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: name === 'studyMinutes' ? parseInt(value) || 0 : value
        }));
    };

    // フォーム送信時のハンドラー
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            });
            if (response.ok) {
                // 成功時の処理（フォームクリアなど）
                setFormData({
                    title: '',
                    content: '',
                    studyMinutes: 0
                });
                alert('記録を保存しました！');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('記録の保存に失敗しました');
        }
    };

    return (
        <form onSubmit={handleSubmit} className="max-w-md mx-auto mt-8 p-4">
            <div className="mb-4">
                <label className="block mb-2">
                    学習タイトル:
                    <input
                        type="text"
                        name="title"
                        value={formData.title}
                        onChange={handleChange}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
            </div>

            <div className="mb-4">
                <label className="block mb-2">
                    学習内容:
                    <textarea
                        name="content"
                        value={formData.content}
                        onChange={handleChange}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
            </div>

            <div className="mb-4">
                <label className="block mb-2">
                    学習時間（分）:
                    <input
                        type="number"
                        name="studyMinutes"
                        value={formData.studyMinutes}
                        onChange={handleChange}
                        className="w-full p-2 border rounded"
                        required
                        min="0"
                    />
                </label>
            </div>

            <button
                type="submit"
                className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600"
            >
                記録を保存
            </button>
        </form>
    );
};

export default LearningRecordForm;