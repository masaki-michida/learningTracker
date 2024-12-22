import React, { useState, FormEvent } from 'react';

interface LoginFormProps {
    onLogin: (token: string) => void;
}

const LoginForm: React.FC<LoginFormProps> = ({ onLogin }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });

            if (response.ok) {
                const data = await response.json();
                onLogin(data.token);
            } else {
                console.error('Login failed:', response.statusText);
            }
        } catch (error) {
            console.error('Login error:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="max-w-md mx-auto mt-8 p-4">
            <div className="mb-4">
                <label className="block mb-2">
                    メールアドレス:
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
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
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
            </div>
            <button
                type="submit"
                className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600"
            >
                ログイン
            </button>
        </form>
    );
};

export default LoginForm; 