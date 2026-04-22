package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val uiState by viewModel.uiState.collectAsState()
                
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    CalculatorScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = uiState,
                        onNumberClick = viewModel::onNumberClick,
                        onOperationClick = viewModel::onOperationClick,
                        onEqualsClick = viewModel::onEqualsClick,
                        onClearClick = viewModel::onClearClick,
                        onTrigClick = viewModel::onTrigClick,
                        onToggleAngleUnit = viewModel::toggleAngleUnit
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    uiState: CalculatorUiState,
    onNumberClick: (String) -> Unit,
    onOperationClick: (String) -> Unit,
    onEqualsClick: () -> Unit,
    onClearClick: () -> Unit,
    onTrigClick: (String) -> Unit,
    onToggleAngleUnit: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Individual references for constraints
        val display = createRef()
        val refSin = createRef()
        val refCos = createRef()
        val refTan = createRef()
        val refDegRad = createRef()
        val ref7 = createRef()
        val ref8 = createRef()
        val ref9 = createRef()
        val refDiv = createRef()
        val ref4 = createRef()
        val ref5 = createRef()
        val ref6 = createRef()
        val refMul = createRef()
        val ref1 = createRef()
        val ref2 = createRef()
        val ref3 = createRef()
        val refSub = createRef()
        val refC = createRef()
        val ref0 = createRef()
        val refEq = createRef()
        val refAdd = createRef()

        val rowSpacing = 8.dp

        // Horizontal Chains for Rows
        createHorizontalChain(refSin, refCos, refTan, refDegRad, chainStyle = ChainStyle.Spread)
        createHorizontalChain(ref7, ref8, ref9, refDiv, chainStyle = ChainStyle.Spread)
        createHorizontalChain(ref4, ref5, ref6, refMul, chainStyle = ChainStyle.Spread)
        createHorizontalChain(ref1, ref2, ref3, refSub, chainStyle = ChainStyle.Spread)
        createHorizontalChain(refC, ref0, refEq, refAdd, chainStyle = ChainStyle.Spread)

        // Row 5 (Bottom Row)
        CalcButton(
            text = "C",
            modifier = Modifier.constrainAs(refC) {
                bottom.linkTo(parent.bottom)
                width = Dimension.percent(0.23f)
            },
            color = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer,
            onClick = onClearClick
        )
        CalcButton(
            text = "0",
            modifier = Modifier.constrainAs(ref0) {
                bottom.linkTo(parent.bottom)
                width = Dimension.percent(0.23f)
            },
            onClick = { onNumberClick("0") }
        )
        CalcButton(
            text = "=",
            modifier = Modifier.constrainAs(refEq) {
                bottom.linkTo(parent.bottom)
                width = Dimension.percent(0.23f)
            },
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            onClick = onEqualsClick
        )
        CalcButton(
            text = "+",
            modifier = Modifier.constrainAs(refAdd) {
                bottom.linkTo(parent.bottom)
                width = Dimension.percent(0.23f)
            },
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            onClick = { onOperationClick("+") }
        )

        // Row 4
        CalcButton(
            text = "1",
            modifier = Modifier.constrainAs(ref1) {
                bottom.linkTo(refC.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            onClick = { onNumberClick("1") }
        )
        CalcButton(
            text = "2",
            modifier = Modifier.constrainAs(ref2) {
                bottom.linkTo(ref0.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            onClick = { onNumberClick("2") }
        )
        CalcButton(
            text = "3",
            modifier = Modifier.constrainAs(ref3) {
                bottom.linkTo(refEq.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            onClick = { onNumberClick("3") }
        )
        CalcButton(
            text = "-",
            modifier = Modifier.constrainAs(refSub) {
                bottom.linkTo(refAdd.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            onClick = { onOperationClick("-") }
        )

        // Row 3
        CalcButton(
            text = "4",
            modifier = Modifier.constrainAs(ref4) {
                bottom.linkTo(ref1.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            onClick = { onNumberClick("4") }
        )
        CalcButton(
            text = "5",
            modifier = Modifier.constrainAs(ref5) {
                bottom.linkTo(ref2.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            onClick = { onNumberClick("5") }
        )
        CalcButton(
            text = "6",
            modifier = Modifier.constrainAs(ref6) {
                bottom.linkTo(ref3.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            onClick = { onNumberClick("6") }
        )
        CalcButton(
            text = "*",
            modifier = Modifier.constrainAs(refMul) {
                bottom.linkTo(refSub.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            onClick = { onOperationClick("*") }
        )

        // Row 2
        CalcButton(
            text = "7",
            modifier = Modifier.constrainAs(ref7) {
                bottom.linkTo(ref4.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            onClick = { onNumberClick("7") }
        )
        CalcButton(
            text = "8",
            modifier = Modifier.constrainAs(ref8) {
                bottom.linkTo(ref5.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            onClick = { onNumberClick("8") }
        )
        CalcButton(
            text = "9",
            modifier = Modifier.constrainAs(ref9) {
                bottom.linkTo(ref6.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            onClick = { onNumberClick("9") }
        )
        CalcButton(
            text = "/",
            modifier = Modifier.constrainAs(refDiv) {
                bottom.linkTo(refMul.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            onClick = { onOperationClick("/") }
        )

        // Row 1
        CalcButton(
            text = "Sin",
            modifier = Modifier.constrainAs(refSin) {
                bottom.linkTo(ref7.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            color = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            onClick = { onTrigClick("sin") }
        )
        CalcButton(
            text = "Cos",
            modifier = Modifier.constrainAs(refCos) {
                bottom.linkTo(ref8.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            color = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            onClick = { onTrigClick("cos") }
        )
        CalcButton(
            text = "Tan",
            modifier = Modifier.constrainAs(refTan) {
                bottom.linkTo(ref9.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            color = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            onClick = { onTrigClick("tan") }
        )
        CalcButton(
            text = if (uiState.isDegreeMode) "DEG" else "RAD",
            modifier = Modifier.constrainAs(refDegRad) {
                bottom.linkTo(refDiv.top, margin = rowSpacing)
                width = Dimension.percent(0.23f)
            },
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            onClick = onToggleAngleUnit
        )

        // Display Area (Top)
        Surface(
            modifier = Modifier
                .constrainAs(display) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(refSin.top, margin = 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = uiState.expression,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = uiState.displayValue,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = when {
                            uiState.displayValue.length > 12 -> 32.sp
                            uiState.displayValue.length > 8 -> 40.sp
                            else -> 56.sp
                        },
                        letterSpacing = (-1).sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun CalcButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1.1f) // Keep them slightly rectangular to fit better vertically
            .clip(RoundedCornerShape(16.dp))
            .background(color)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = contentColor
        )
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CalculatorPreview() {
    MyApplicationTheme {
        CalculatorScreen(
            uiState = CalculatorUiState(
                displayValue = "123.456",
                expression = "50 + 73.456",
                isDegreeMode = true
            ),
            onNumberClick = {},
            onOperationClick = {},
            onEqualsClick = {},
            onClearClick = {},
            onTrigClick = {},
            onToggleAngleUnit = {}
        )
    }
}
