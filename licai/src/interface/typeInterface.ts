export interface PlatInfoType {
    historyAvgRate?: number,
    registerUsers?: number,
    sumBigMoney?: number
}

export interface Product {
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
    noviceTreasure?: Product[];
    preferred?: Product[];
    scatterLabel?: Product[];
}