export interface LearningRecord {
    id: number;
    title: string;
    content: string;
    studyMinutes: number;
    createdAt: string;
    updatedAt: string;
}

// フォーム入力用の型（作成時に使用）
export type LearningRecordInput = Omit<LearningRecord, 'id' | 'createdAt' | 'updatedAt'>;