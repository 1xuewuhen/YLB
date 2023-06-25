export interface PlatInfoType {
    historyAvgRate?: number,
    registerUsers?: number,
    sumBigMoney?: number
}

export interface Product1 {
    id?: number;
    productName?: string;
    rate?: number;
    cycle?: number;
    releaseTime?: number;
    productType?: number;
    productNo?: string;
    productMoney?: number;
    leftProductMoney?: number;
    bidMinLimit?: number;
    bidMaxLimit?: number;
    productStatus?: number;
    productFullTime?: number;
    productDesc?: string;
    version?: number;
}

export interface ProductType {
    noviceTreasure?: Product1[];
    preferred?: Product1[];
    scatterLabel?: Product1[];
}