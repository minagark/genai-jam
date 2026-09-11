import type { ReactNode } from "react";

type Credit = { name: string; role: string };

const credits: Credit[] = [
    {name: "John", role: "developer"},
    {name: "Jane", role: "developer"},
    {name: "Jack", role: "developer"},
    {name: "Jill", role: "developer"},
    {name: "James", role: "developer"},
    {name: "Jenny", role: "developer"},
    {name: "Jade", role: "developer"},
    {name: "Jasmine", role: "developer"},
    {name: "Jasper", role: "developer"},
    {name: "Jared", role: "developer"},
    {name: "Jocelyn", role: "developer"},
    {name: "Jude", role: "developer"},
    {name: "Jules", role: "developer"},
    {name: "Julian", role: "developer"},
    {name: "Julia", role: "developer"}
];

let cachedCredits: ReactNode[] | null = null;

const renderCredit = (credit: Credit, index: number) => {
    const start = performance.now();
    while(performance.now() -  start  < 100) {
        //do nothing - create a delay to simulate a long operation
    }
    return <p key={`${credit.name}-${index}`}>{credit.name} - {credit.role}</p>;
};

const getCredits = () => {
    if (!cachedCredits) {
        cachedCredits = credits.map((credit, index) => renderCredit(credit, index));
    }

    return cachedCredits;
};

const Credits = () => {
    return (<div>This app was written by:
        {getCredits()}
    </div>);
};

export default Credits;
