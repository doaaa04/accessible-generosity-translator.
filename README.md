# Accessible Generosity Translator 

A lightweight Java CLI application powered by Google Gemini API that transforms unstructured local aid requests into transparent, professional English fundraising briefs.

Built for the **DEV.to Weekend Challenge: Generosity Edition** (Category: *Best Use of Google AI*).

## Problem & Impact
Grassroots volunteers in conflict zones and under-resourced communities (e.g., Gaza/Palestine) frequently post urgent aid requests on social media in local dialects. These informal posts lack the language fluency, budgeting breakdown, and formal structure required by international donor networks.

This tool bridges that gap by parsing raw contextual input, extracting financial and quantity data, and generating structured Markdown campaign reports ready for donor platforms.

## How It Works
1. Accepts raw Arabic notes from terminal input.
2. Sends the request via zero-dependency HTTP calls to **Google Gemini 1.5 Flash**.
3. Enforces strict prompt rules to extract items, calculate totals, format tables, and append transparency disclaimers.

## Tech Stack
* **Language:** Java (Native HTTP Client, zero external dependencies)
* **AI Model:** Google Gemini 1.5 Flash (`generativelanguage.googleapis.com`)

## Sample Output Demo

### Input (Raw Local Request)
> "نحن مجموعة متطوعين في خانيونس نحتاج بشكل عاجل 50 طرد غذائي و20 بطانية للأسر النازحة، التكلفة الإجمالية نحو 1500 دولار."

### Output (Generated Campaign Brief)
#### Emergency Food & Shelter Relief in Khan Younis

**Urgent Need & Context**
A local volunteer team in Khan Younis is requesting immediate assistance to provide basic food and shelter items for displaced families facing critical shortages.

| Item | Quantity | Estimated Cost (USD) |
| :--- | :--- | :--- |
| Food Packages | 50 | $1,100 |
| Blankets | 20 | $400 |
| **Total** | | **$1,500** |

**Verification & Transparency Note**
*Direct grassroots request. Donors are advised to verify local delivery channels and request itemized purchase receipts upon distribution.*

## How to Run Locally
1. Clone the repository:
   ```bash
   git clone [https://github.com/YOUR_GITHUB_USERNAME/accessible-generosity-translator.git](https://github.com/YOUR_GITHUB_USERNAME/accessible-generosity-translator.git)
