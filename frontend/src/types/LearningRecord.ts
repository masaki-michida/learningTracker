export interface LearningRecord {
    id?: number;           // Optional(?)にしているのは、新規作成時にはIDがないため
    title: string;         // 学習タイトル
    content: string;       // 学習内容
    studyMinutes: number;  // 学習時間（分）
    createdAt?: string;    // 作成日時（バックエンドで自動設定）
}

// フォーム入力用の型（作成時に使用）
export type LearningRecordInput = Omit<LearningRecord, 'id' | 'createdAt'>;