import React, { useState, FormEvent } from 'react';

interface RegisterFormProps {
    onRegisterSuccess: () => void;
}

const RegisterForm: React.FC<RegisterFormProps> = ({ onRegisterSuccess }) => {
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: '',
        confirmPassword: ''
    });
    const [error, setError] = useState<string>('');

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        setError('');

        try {
            if (!formData.username || !formData.email || !formData.password) {
                setError('すべての項目を入力してください');
                return;
            }

            if (formData.password !== formData.confirmPassword) {
                setError('パスワードが一致しません');
                return;
            }

            const response = await fetch('http://localhost:8080/api/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify({
                    username: formData.username.trim(),
                    email: formData.email.trim(),
                    password: formData.password
                }),
            });

            const data = await response.json();
            console.log('Server response:', data); // デバッグ用

            if (!response.ok) {
                throw new Error(data.message || '登録に失敗しました');
            }

            alert(data.message);
            onRegisterSuccess();

        } catch (error) {
            console.error('Registration error:', error);
            setError(error instanceof Error ? error.message : 'サーバーエラーが発生しました');
        }
    };

    return (
        <form onSubmit={handleSubmit} className="max-w-md mx-auto mt-8 p-4">
            {error && (
                <div className="mb-4 p-2 bg-red-100 text-red-700 rounded">
                    {error}
                </div>
            )}
            <div className="mb-4">
                <label className="block mb-2">
                    ユーザー名:
                    <input
                        type="text"
                        value={formData.username}
                        onChange={(e) => setFormData({...formData, username: e.target.value})}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
            </div>
            <div className="mb-4">
                <label className="block mb-2">
                    メールアドレス:
                    <input
                        type="email"
                        value={formData.email}
                        onChange={(e) => setFormData({...formData, email: e.target.value})}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
            </div>
            <div className="mb-4">
                <label className="block mb-2">
                    パスワード:
                    <input
                        type="password"
                        value={formData.password}
                        onChange={(e) => setFormData({...formData, password: e.target.value})}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
            </div>
            <div className="mb-4">
                <label className="block mb-2">
                    パスワード（確認）:
                    <input
                        type="password"
                        value={formData.confirmPassword}
                        onChange={(e) => setFormData({...formData, confirmPassword: e.target.value})}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
            </div>
            <button
                type="submit"
                className="w-full bg-green-500 text-white p-2 rounded hover:bg-green-600"
            >
                登録
            </button>
        </form>
    );
};

export default RegisterForm; 