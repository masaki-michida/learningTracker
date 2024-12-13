// src/App.tsx
import LearningRecordForm from './components/LearningRecordForm';

function App() {
  return (
    <div className="container mx-auto px-4">
      <h1 className="text-2xl font-bold text-center my-8">学習記録アプリ</h1>
      <LearningRecordForm />
    </div>
  );
}

export default App;