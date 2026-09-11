// AboutPage.tsx
import { Suspense, lazy, useState, useTransition } from "react";
const Credits = lazy(() => import("../credits/Credits"));

const AboutPage = () => {
    const [showCredits, setShowCredits] = useState(false);
    const [isPending, startTransition] = useTransition();

    const handleClick = () => {
        startTransition(() => {
            setShowCredits(!showCredits);
        });
    }

    return (
        <div>
            <h1>About Page</h1>
            <p>This is the payments app</p>
            <button onClick={handleClick} disabled={isPending}>
                {showCredits ? "hide" : "show"} credits
            </button>
            {showCredits && (
                <Suspense fallback={<p>Loading credits...</p>}>
                    <Credits />
                </Suspense>
            )}
        </div>
    );
}
export default AboutPage;
